package sat.gdil.emploi.service;

import java.util.List;
import java.util.Optional;
import sat.gdil.emploi.service.dto.LocalisationDTO;

/**
 * Service Interface for managing {@link sat.gdil.emploi.domain.Localisation}.
 */
public interface LocalisationService {
    /**
     * Save a localisation.
     *
     * @param localisationDTO the entity to save.
     * @return the persisted entity.
     */
    LocalisationDTO save(LocalisationDTO localisationDTO);

    /**
     * Updates a localisation.
     *
     * @param localisationDTO the entity to update.
     * @return the persisted entity.
     */
    LocalisationDTO update(LocalisationDTO localisationDTO);

    /**
     * Partially updates a localisation.
     *
     * @param localisationDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<LocalisationDTO> partialUpdate(LocalisationDTO localisationDTO);

    /**
     * Get all the localisations.
     *
     * @return the list of entities.
     */
    List<LocalisationDTO> findAll();

    /**
     * Get the "id" localisation.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LocalisationDTO> findOne(Long id);

    /**
     * Delete the "id" localisation.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
