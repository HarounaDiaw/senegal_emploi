package sat.gdil.emploi.service;

import java.util.List;
import java.util.Optional;
import sat.gdil.emploi.service.dto.OffreEmploiDTO;

/**
 * Service Interface for managing {@link sat.gdil.emploi.domain.OffreEmploi}.
 */
public interface OffreEmploiService {
    /**
     * Save a offreEmploi.
     *
     * @param offreEmploiDTO the entity to save.
     * @return the persisted entity.
     */
    OffreEmploiDTO save(OffreEmploiDTO offreEmploiDTO);

    /**
     * Updates a offreEmploi.
     *
     * @param offreEmploiDTO the entity to update.
     * @return the persisted entity.
     */
    OffreEmploiDTO update(OffreEmploiDTO offreEmploiDTO);

    /**
     * Partially updates a offreEmploi.
     *
     * @param offreEmploiDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OffreEmploiDTO> partialUpdate(OffreEmploiDTO offreEmploiDTO);

    /**
     * Get all the offreEmplois.
     *
     * @return the list of entities.
     */
    List<OffreEmploiDTO> findAll();

    /**
     * Get the "id" offreEmploi.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OffreEmploiDTO> findOne(Long id);

    /**
     * Delete the "id" offreEmploi.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
