package sat.gdil.emploi.service;

import java.util.List;
import java.util.Optional;
import sat.gdil.emploi.service.dto.CandidatDTO;

/**
 * Service Interface for managing {@link sat.gdil.emploi.domain.Candidat}.
 */
public interface CandidatService {
    /**
     * Save a candidat.
     *
     * @param candidatDTO the entity to save.
     * @return the persisted entity.
     */
    CandidatDTO save(CandidatDTO candidatDTO);

    /**
     * Updates a candidat.
     *
     * @param candidatDTO the entity to update.
     * @return the persisted entity.
     */
    CandidatDTO update(CandidatDTO candidatDTO);

    /**
     * Partially updates a candidat.
     *
     * @param candidatDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<CandidatDTO> partialUpdate(CandidatDTO candidatDTO);

    /**
     * Get all the candidats.
     *
     * @return the list of entities.
     */
    List<CandidatDTO> findAll();

    /**
     * Get the "id" candidat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<CandidatDTO> findOne(Long id);

    /**
     * Delete the "id" candidat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
