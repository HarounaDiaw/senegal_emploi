package sat.gdil.emploi.service;

import java.util.List;
import java.util.Optional;
import sat.gdil.emploi.service.dto.CandidatureDTO;

/**
 * Service Interface for managing {@link sat.gdil.emploi.domain.Candidature}.
 */
public interface CandidatureService {
    /**
     * Save a candidature.
     *
     * @param candidatureDTO the entity to save.
     * @return the persisted entity.
     */
    CandidatureDTO save(CandidatureDTO candidatureDTO);

    /**
     * Updates a candidature.
     *
     * @param candidatureDTO the entity to update.
     * @return the persisted entity.
     */
    CandidatureDTO update(CandidatureDTO candidatureDTO);

    /**
     * Partially updates a candidature.
     *
     * @param candidatureDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CandidatureDTO> partialUpdate(CandidatureDTO candidatureDTO);

    /**
     * Get all the candidatures.
     *
     * @return the list of entities.
     */
    List<CandidatureDTO> findAll();

    /**
     * Get the "id" candidature.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CandidatureDTO> findOne(Long id);

    /**
     * Delete the "id" candidature.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
