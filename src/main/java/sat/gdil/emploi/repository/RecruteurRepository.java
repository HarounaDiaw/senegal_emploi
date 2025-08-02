package sat.gdil.emploi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sat.gdil.emploi.domain.Recruteur;

/**
 * Spring Data JPA repository for the Recruteur entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RecruteurRepository extends JpaRepository<Recruteur, Long> {}
