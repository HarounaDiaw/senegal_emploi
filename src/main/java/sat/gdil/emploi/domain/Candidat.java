package sat.gdil.emploi.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import sat.gdil.emploi.domain.enumeration.Sexe;

/**
 * A Candidat.
 */
@Entity
@Table(name = "candidat")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Candidat implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "cv")
    private String cv;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "adresse")
    private String adresse;

    @Enumerated(EnumType.STRING)
    @Column(name = "sexe")
    private Sexe sexe;

    @Column(name = "photo")
    private String photo;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private User user;

    public String getNom() {
        return user != null ? user.getLastName() : null;
    }

    public String getPrenom() {
        return user != null ? user.getFirstName() : null;
    }

    public void setNom(String nom) {
        if (user != null) user.setLastName(nom);
    }

    public void setPrenom(String prenom) {
        if (user != null) user.setFirstName(prenom);
    }

    public User getUser() {
        return user;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Candidat id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCv() {
        return this.cv;
    }

    public Candidat cv(String cv) {
        this.setCv(cv);
        return this;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public Candidat telephone(String telephone) {
        this.setTelephone(telephone);
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public Candidat adresse(String adresse) {
        this.setAdresse(adresse);
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Sexe getSexe() {
        return this.sexe;
    }

    public Candidat sexe(Sexe sexe) {
        this.setSexe(sexe);
        return this;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getPhoto() {
        return this.photo;
    }

    public Candidat photo(String photo) {
        this.setPhoto(photo);
        return this;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Candidat)) {
            return false;
        }
        return getId() != null && getId().equals(((Candidat) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Candidat{" +
            "id=" + getId() +
            ", cv='" + getCv() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", sexe='" + getSexe() + "'" +
            ", photo='" + getPhoto() + "'" +
            "}";
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
