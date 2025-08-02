package sat.gdil.emploi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sat.gdil.emploi.domain.Candidature;

/**
 * Spring Data JPA repository for the Candidature entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {}
