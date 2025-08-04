package sat.gdil.emploi.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sat.gdil.emploi.domain.Candidat;
import sat.gdil.emploi.repository.CandidatRepository;
import sat.gdil.emploi.security.SecurityUtils;
import sat.gdil.emploi.service.CandidatService;
import sat.gdil.emploi.service.dto.CandidatDTO;
import sat.gdil.emploi.service.mapper.CandidatMapper;

/**
 * Service Implementation for managing {@link sat.gdil.emploi.domain.Candidat}.
 */
@Service
@Transactional
public class CandidatServiceImpl implements CandidatService {

    private static final Logger LOG = LoggerFactory.getLogger(CandidatServiceImpl.class);

    private final CandidatRepository candidatRepository;

    private final CandidatMapper candidatMapper;

    public CandidatServiceImpl(CandidatRepository candidatRepository, CandidatMapper candidatMapper) {
        this.candidatRepository = candidatRepository;
        this.candidatMapper = candidatMapper;
    }

    @Override
    public CandidatDTO save(CandidatDTO candidatDTO) {
        LOG.debug("Request to save Candidat : {}", candidatDTO);
        Candidat candidat = candidatMapper.toEntity(candidatDTO);
        candidat = candidatRepository.save(candidat);
        return candidatMapper.toDto(candidat);
    }

    @Override
    public CandidatDTO update(CandidatDTO candidatDTO) {
        LOG.debug("Request to update Candidat : {}", candidatDTO);
        Candidat candidat = candidatMapper.toEntity(candidatDTO);
        candidat = candidatRepository.save(candidat);
        return candidatMapper.toDto(candidat);
    }

    @Override
    public Optional<CandidatDTO> partialUpdate(CandidatDTO candidatDTO) {
        LOG.debug("Request to partially update Candidat : {}", candidatDTO);

        return candidatRepository
            .findById(candidatDTO.getId())
            .map(existingCandidat -> {
                candidatMapper.partialUpdate(existingCandidat, candidatDTO);

                return existingCandidat;
            })
            .map(candidatRepository::save)
            .map(candidatMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidatDTO> findAll() {
        LOG.debug("Request to get all Candidats");
        return candidatRepository.findAll().stream().map(candidatMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CandidatDTO> findOne(Long id) {
        LOG.debug("Request to get Candidat : {}", id);
        return candidatRepository.findById(id).map(candidatMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Candidat : {}", id);
        candidatRepository.deleteById(id);
    }

    public CandidatDTO getCurrentCandidatDTO() {
        String login = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new RuntimeException("Utilisateur non connecté"));

        Candidat candidat = candidatRepository
            .findOneWithEagerRelationshipsByLogin(login)
            .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        // Création manuelle du DTO
        CandidatDTO dto = candidatMapper.toDto(candidat);
        dto.setNom(candidat.getUser().getLastName()); // ⚠️ attention : getUser()
        dto.setPrenom(candidat.getUser().getFirstName()); // idem

        return dto;
    }

    public CandidatDTO updateCurrentCandidat(CandidatDTO dto) {
        String login = SecurityUtils.getCurrentUserLogin().orElseThrow(() -> new RuntimeException("Utilisateur non connecté"));

        Candidat candidat = candidatRepository
            .findOneWithEagerRelationshipsByLogin(login)
            .orElseThrow(() -> new RuntimeException("Candidat non trouvé"));

        // Mise à jour des champs simples du candidat
        candidat.setAdresse(dto.getAdresse());
        candidat.setTelephone(dto.getTelephone());
        candidat.setSexe(dto.getSexe());
        candidat.setCv(dto.getCv());
        candidat.setPhoto(dto.getPhoto());

        // Mise à jour des champs liés à User
        if (candidat.getUser() != null) {
            candidat.getUser().setFirstName(dto.getPrenom());
            candidat.getUser().setLastName(dto.getNom());
            candidat.getUser().setEmail(dto.getEmail()); // Optionnel
        }

        candidat = candidatRepository.save(candidat);
        return getCurrentCandidatDTO(); // renvoyer l'état à jour
    }

    @Override
    public String storePhoto(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        // Solution propre : accéder au chemin réel du classpath (target/classes/static)
        Path root = Paths.get(new ClassPathResource("static/content/images/candidats/photo").getURI());

        Files.createDirectories(root);
        Files.copy(file.getInputStream(), root.resolve(filename), StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }

    @Override
    public String storeCv(MultipartFile file) throws IOException {
        String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();

        Path path = Paths.get("src/main/resources/static/content/images/candidats/cv/" + filename);

        Files.createDirectories(path.getParent());
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

        return filename;
    }
}
