package sat.gdil.emploi.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link sat.gdil.emploi.domain.Recruteur} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RecruteurDTO implements Serializable {

    private Long id;

    private String entreprise;

    private String secteur;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getSecteur() {
        return secteur;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RecruteurDTO)) {
            return false;
        }

        RecruteurDTO recruteurDTO = (RecruteurDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, recruteurDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RecruteurDTO{" +
            "id=" + getId() +
            ", entreprise='" + getEntreprise() + "'" +
            ", secteur='" + getSecteur() + "'" +
            "}";
    }
}
