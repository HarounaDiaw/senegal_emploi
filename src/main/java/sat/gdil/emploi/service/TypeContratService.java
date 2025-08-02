package sat.gdil.emploi.service;

import java.util.List;
import java.util.Optional;
import sat.gdil.emploi.service.dto.TypeContratDTO;

/**
 * Service Interface for managing {@link sat.gdil.emploi.domain.TypeContrat}.
 */
public interface TypeContratService {
    /**
     * Save a typeContrat.
     *
     * @param typeContratDTO the entity to save.
     * @return the persisted entity.
     */
    TypeContratDTO save(TypeContratDTO typeContratDTO);

    /**
     * Updates a typeContrat.
     *
     * @param typeContratDTO the entity to update.
     * @return the persisted entity.
     */
    TypeContratDTO update(TypeContratDTO typeContratDTO);

    /**
     * Partially updates a typeContrat.
     *
     * @param typeContratDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<TypeContratDTO> partialUpdate(TypeContratDTO typeContratDTO);

    /**
     * Get all the typeContrats.
     *
     * @return the list of entities.
     */
    List<TypeContratDTO> findAll();

    /**
     * Get the "id" typeContrat.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeContratDTO> findOne(Long id);

    /**
     * Delete the "id" typeContrat.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
