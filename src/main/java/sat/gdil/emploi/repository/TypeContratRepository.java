package sat.gdil.emploi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sat.gdil.emploi.domain.TypeContrat;

/**
 * Spring Data JPA repository for the TypeContrat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TypeContratRepository extends JpaRepository<TypeContrat, Long> {}
