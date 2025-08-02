package sat.gdil.emploi.web.rest;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sat.gdil.emploi.domain.Candidat;
import sat.gdil.emploi.domain.Recruteur;
import sat.gdil.emploi.domain.User;
import sat.gdil.emploi.repository.CandidatRepository;
import sat.gdil.emploi.repository.RecruteurRepository;
import sat.gdil.emploi.repository.UserRepository;
import sat.gdil.emploi.security.SecurityUtils;
import sat.gdil.emploi.service.MailService;
import sat.gdil.emploi.service.UserService;
import sat.gdil.emploi.service.dto.AdminUserDTO;
import sat.gdil.emploi.service.dto.PasswordChangeDTO;
import sat.gdil.emploi.web.rest.errors.*;
import sat.gdil.emploi.web.rest.vm.KeyAndPasswordVM;
import sat.gdil.emploi.web.rest.vm.ManagedUserVM;

/**
 * REST controller for managing the current user's account.
 */
@RestController
@RequestMapping("/api")
public class AccountResource {

    private static class AccountResourceException extends RuntimeException {

        private AccountResourceException(String message) {
            super(message);
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(AccountResource.class);

    private final UserRepository userRepository;

    private final UserService userService;

    private final MailService mailService;
    private final CandidatRepository candidatRepository;
    private final RecruteurRepository recruteurRepository;

    public AccountResource(
        UserRepository userRepository,
        UserService userService,
        MailService mailService,
        CandidatRepository candidatRepository,
        RecruteurRepository recruteurRepository
    ) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.mailService = mailService;
        this.candidatRepository = candidatRepository;
        this.recruteurRepository = recruteurRepository;
    }

    /**
     * {@code POST  /register} : register the user.
     *
     * @param managedUserVM the managed user View Model.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws LoginAlreadyUsedException {@code 400 (Bad Request)} if the login is already used.
     */
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void registerAccount(@Valid @RequestBody ManagedUserVM managedUserVM) {
        if (isPasswordLengthInvalid(managedUserVM.getPassword())) {
            throw new InvalidPasswordException();
        }

        // Vérification des conflits (login ou email déjà utilisé)
        userRepository
            .findOneByLogin(managedUserVM.getLogin().toLowerCase())
            .ifPresent(existingUser -> {
                throw new LoginAlreadyUsedException();
            });

        userRepository
            .findOneByEmailIgnoreCase(managedUserVM.getEmail())
            .ifPresent(existingUser -> {
                throw new EmailAlreadyUsedException();
            });

        // Création de l'utilisateur
        User user = userService.registerUser(managedUserVM, managedUserVM.getPassword());

        // ⚠️ Forcer Hibernate à flush immédiatement l'utilisateur pour éviter l'erreur 409
        userRepository.flush();

        // Création du Candidat lié (avec @MapsId)
        if ("CANDIDAT".equalsIgnoreCase(managedUserVM.getType())) {
            Candidat candidat = new Candidat();
            candidat.setUser(user);
            candidat.setTelephone(managedUserVM.getTelephone());
            candidat.setAdresse(managedUserVM.getAdresse());
            candidat.setSexe(managedUserVM.getSexe());
            candidat.setPhoto(managedUserVM.getPhoto());
            candidatRepository.save(candidat);
        } else if ("RECRUTEUR".equalsIgnoreCase(managedUserVM.getType())) {
            Recruteur recruteur = new Recruteur();
            recruteur.setUser(user);
            recruteur.setEntreprise(managedUserVM.getEntreprise()); // ajoute ce champ dans VM si besoin
            recruteur.setSecteur(managedUserVM.getSecteur());
            recruteurRepository.save(recruteur);
        } else {
            throw new BadRequestAlertException("Type d'utilisateur inconnu", "user", "invalidtype");
        }

        // Envoi de l’email d’activation
        mailService.sendActivationEmail(user);
    }

    /**
     * {@code GET  /activate} : activate the registered user.
     *
     * @param key the activation key.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be activated.
     */
    @GetMapping("/activate")
    public void activateAccount(@RequestParam(value = "key") String key) {
        Optional<User> user = userService.activateRegistration(key);
        if (!user.isPresent()) {
            throw new AccountResourceException("No user was found for this activation key");
        }
    }

    /**
     * {@code GET  /account} : get the current user.
     *
     * @return the current user.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user couldn't be returned.
     */
    @GetMapping("/account")
    public AdminUserDTO getAccount() {
        return userService
            .getUserWithAuthorities()
            .map(AdminUserDTO::new)
            .orElseThrow(() -> new AccountResourceException("User could not be found"));
    }

    /**
     * {@code POST  /account} : update the current user information.
     *
     * @param userDTO the current user information.
     * @throws EmailAlreadyUsedException {@code 400 (Bad Request)} if the email is already used.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the user login wasn't found.
     */
    @PostMapping("/account")
    public void saveAccount(@Valid @RequestBody AdminUserDTO userDTO) {
        String userLogin = SecurityUtils.getCurrentUserLogin()
            .orElseThrow(() -> new AccountResourceException("Current user login not found"));
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.orElseThrow().getLogin().equalsIgnoreCase(userLogin))) {
            throw new EmailAlreadyUsedException();
        }
        Optional<User> user = userRepository.findOneByLogin(userLogin);
        if (!user.isPresent()) {
            throw new AccountResourceException("User could not be found");
        }
        userService.updateUser(
            userDTO.getFirstName(),
            userDTO.getLastName(),
            userDTO.getEmail(),
            userDTO.getLangKey(),
            userDTO.getImageUrl()
        );
    }

    /**
     * {@code POST  /account/change-password} : changes the current user's password.
     *
     * @param passwordChangeDto current and new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the new password is incorrect.
     */
    @PostMapping(path = "/account/change-password")
    public void changePassword(@RequestBody PasswordChangeDTO passwordChangeDto) {
        if (isPasswordLengthInvalid(passwordChangeDto.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    /**
     * {@code POST   /account/reset-password/init} : Send an email to reset the password of the user.
     *
     * @param mail the mail of the user.
     */
    @PostMapping(path = "/account/reset-password/init")
    public void requestPasswordReset(@RequestBody String mail) {
        Optional<User> user = userService.requestPasswordReset(mail);
        if (user.isPresent()) {
            mailService.sendPasswordResetMail(user.orElseThrow());
        } else {
            // Pretend the request has been successful to prevent checking which emails really exist
            // but log that an invalid attempt has been made
            LOG.warn("Password reset requested for non existing mail");
        }
    }

    /**
     * {@code POST   /account/reset-password/finish} : Finish to reset the password of the user.
     *
     * @param keyAndPassword the generated key and the new password.
     * @throws InvalidPasswordException {@code 400 (Bad Request)} if the password is incorrect.
     * @throws RuntimeException {@code 500 (Internal Server Error)} if the password could not be reset.
     */
    @PostMapping(path = "/account/reset-password/finish")
    public void finishPasswordReset(@RequestBody KeyAndPasswordVM keyAndPassword) {
        if (isPasswordLengthInvalid(keyAndPassword.getNewPassword())) {
            throw new InvalidPasswordException();
        }
        Optional<User> user = userService.completePasswordReset(keyAndPassword.getNewPassword(), keyAndPassword.getKey());

        if (!user.isPresent()) {
            throw new AccountResourceException("No user was found for this reset key");
        }
    }

    private static boolean isPasswordLengthInvalid(String password) {
        return (
            StringUtils.isEmpty(password) ||
            password.length() < ManagedUserVM.PASSWORD_MIN_LENGTH ||
            password.length() > ManagedUserVM.PASSWORD_MAX_LENGTH
        );
    }
}
