package sat.gdil.emploi.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sat.gdil.emploi.domain.Candidat;

/**
 * Spring Data JPA repository for the Candidat entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatRepository extends JpaRepository<Candidat, Long> {
    @Query("SELECT c FROM Candidat c JOIN FETCH c.user u WHERE u.login = :login")
    Optional<Candidat> findOneWithEagerRelationshipsByLogin(@Param("login") String login);

    Optional<Candidat> findByUserId(Long userId);
}
