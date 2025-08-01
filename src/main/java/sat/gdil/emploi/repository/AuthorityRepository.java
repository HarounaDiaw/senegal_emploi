package sat.gdil.emploi.repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import sat.gdil.emploi.domain.Authority;

/**
 * Spring Data JPA repository for the Authority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {}
