package sat.gdil.emploi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sat.gdil.emploi.domain.Candidature;

/**
 * Spring Data JPA repository for the Candidature entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CandidatureRepository extends JpaRepository<Candidature, Long> {
    boolean existsByCandidatIdAndOffreId(Long candidatId, Long offreId);

    //liste candidature
    @Query("SELECT c FROM Candidature c JOIN FETCH c.offre WHERE c.candidat.id = :candidatId")
    List<Candidature> findByCandidatIdWithOffre(@Param("candidatId") Long candidatId);
}
