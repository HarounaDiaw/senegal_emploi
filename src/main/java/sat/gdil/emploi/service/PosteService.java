package sat.gdil.emploi.service;

import java.util.List;
import java.util.Optional;
import sat.gdil.emploi.service.dto.PosteDTO;

/**
 * Service Interface for managing {@link sat.gdil.emploi.domain.Poste}.
 */
public interface PosteService {
    /**
     * Save a poste.
     *
     * @param posteDTO the entity to save.
     * @return the persisted entity.
     */
    PosteDTO save(PosteDTO posteDTO);

    /**
     * Updates a poste.
     *
     * @param posteDTO the entity to update.
     * @return the persisted entity.
     */
    PosteDTO update(PosteDTO posteDTO);

    /**
     * Partially updates a poste.
     *
     * @param posteDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<PosteDTO> partialUpdate(PosteDTO posteDTO);

    /**
     * Get all the postes.
     *
     * @return the list of entities.
     */
    List<PosteDTO> findAll();

    /**
     * Get the "id" poste.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PosteDTO> findOne(Long id);

    /**
     * Delete the "id" poste.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
