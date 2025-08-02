package sat.gdil.emploi.service;

import java.util.List;
import java.util.Optional;
import sat.gdil.emploi.service.dto.RecruteurDTO;

/**
 * Service Interface for managing {@link sat.gdil.emploi.domain.Recruteur}.
 */
public interface RecruteurService {
    /**
     * Save a recruteur.
     *
     * @param recruteurDTO the entity to save.
     * @return the persisted entity.
     */
    RecruteurDTO save(RecruteurDTO recruteurDTO);

    /**
     * Updates a recruteur.
     *
     * @param recruteurDTO the entity to update.
     * @return the persisted entity.
     */
    RecruteurDTO update(RecruteurDTO recruteurDTO);

    /**
     * Partially updates a recruteur.
     *
     * @param recruteurDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RecruteurDTO> partialUpdate(RecruteurDTO recruteurDTO);

    /**
     * Get all the recruteurs.
     *
     * @return the list of entities.
     */
    List<RecruteurDTO> findAll();

    /**
     * Get the "id" recruteur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RecruteurDTO> findOne(Long id);

    /**
     * Delete the "id" recruteur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
