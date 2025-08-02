package sat.gdil.emploi.web.rest.vm;

import jakarta.validation.constraints.Size;
import sat.gdil.emploi.domain.enumeration.Sexe;
import sat.gdil.emploi.service.dto.AdminUserDTO;

/**
 * View Model extending the AdminUserDTO, which is meant to be used in the user management UI.
 */
public class ManagedUserVM extends AdminUserDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password;

    //candidat
    private String telephone;
    private String adresse;
    private Sexe sexe;
    private String photo;
    //recruteur
    private String type;
    private String entreprise;
    private String secteur;

    // + leurs getters/setters

    public ManagedUserVM() {
        // Empty constructor needed for Jackson.
    }

    public String getEntreprise() {
        return entreprise;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public String getPhoto() {
        return photo;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ManagedUserVM{" + super.toString() + "} ";
    }
}
