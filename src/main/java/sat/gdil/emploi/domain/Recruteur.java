package sat.gdil.emploi.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Recruteur.
 */
@Entity
@Table(name = "recruteur")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Recruteur implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public User getUser() {
        return user;
    }

    @Column(name = "entreprise")
    private String entreprise;

    @Column(name = "secteur")
    private String secteur;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Recruteur id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntreprise() {
        return this.entreprise;
    }

    public Recruteur entreprise(String entreprise) {
        this.setEntreprise(entreprise);
        return this;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public String getSecteur() {
        return this.secteur;
    }

    public Recruteur secteur(String secteur) {
        this.setSecteur(secteur);
        return this;
    }

    public void setSecteur(String secteur) {
        this.secteur = secteur;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Recruteur)) {
            return false;
        }
        return getId() != null && getId().equals(((Recruteur) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Recruteur{" +
            "id=" + getId() +
            ", entreprise='" + getEntreprise() + "'" +
            ", secteur='" + getSecteur() + "'" +
            "}";
    }

    public void setUser(User user) {
        this.user = user;
    }
}
