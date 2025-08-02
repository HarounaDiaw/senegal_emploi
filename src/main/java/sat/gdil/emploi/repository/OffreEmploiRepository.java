package sat.gdil.emploi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sat.gdil.emploi.domain.OffreEmploi;

/**
 * Spring Data JPA repository for the OffreEmploi entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OffreEmploiRepository extends JpaRepository<OffreEmploi, Long> {}
