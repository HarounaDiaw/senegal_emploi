package sat.gdil.emploi.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sat.gdil.emploi.domain.Candidat;
import sat.gdil.emploi.domain.Candidature;
import sat.gdil.emploi.domain.OffreEmploi;
import sat.gdil.emploi.domain.User;
import sat.gdil.emploi.repository.CandidatRepository;
import sat.gdil.emploi.repository.CandidatureRepository;
import sat.gdil.emploi.repository.OffreEmploiRepository;
import sat.gdil.emploi.service.CandidatureService;
import sat.gdil.emploi.service.UserService;
import sat.gdil.emploi.service.dto.CandidatureDTO;
import sat.gdil.emploi.web.rest.errors.BadRequestAlertException;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link sat.gdil.emploi.domain.Candidature}.
 */
@RestController
@RequestMapping("/api/candidatures")
public class CandidatureResource {

    private static final Logger LOG = LoggerFactory.getLogger(CandidatureResource.class);

    private static final String ENTITY_NAME = "candidature";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CandidatureService candidatureService;

    private final CandidatureRepository candidatureRepository;

    private final OffreEmploiRepository offreEmploiRepository;
    private final CandidatRepository candidatRepository;
    private final UserService userService;

    public CandidatureResource(
        CandidatureService candidatureService,
        CandidatureRepository candidatureRepository,
        OffreEmploiRepository offreEmploiRepository,
        CandidatRepository candidatRepository,
        UserService userService
    ) {
        this.candidatureService = candidatureService;
        this.candidatureRepository = candidatureRepository;
        this.offreEmploiRepository = offreEmploiRepository;
        this.candidatRepository = candidatRepository;
        this.userService = userService;
    }

    /**
     * {@code POST  /candidatures} : Create a new candidature.
     *
     * @param candidatureDTO the candidatureDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new candidatureDTO, or with status {@code 400 (Bad Request)} if the candidature has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CandidatureDTO> createCandidature(@RequestBody CandidatureDTO candidatureDTO) throws URISyntaxException {
        LOG.debug("REST request to save Candidature : {}", candidatureDTO);
        if (candidatureDTO.getId() != null) {
            throw new BadRequestAlertException("A new candidature cannot already have an ID", ENTITY_NAME, "idexists");
        }
        candidatureDTO = candidatureService.save(candidatureDTO);
        return ResponseEntity.created(new URI("/api/candidatures/" + candidatureDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, candidatureDTO.getId().toString()))
            .body(candidatureDTO);
    }

    /**
     * {@code PUT  /candidatures/:id} : Updates an existing candidature.
     *
     * @param id the id of the candidatureDTO to save.
     * @param candidatureDTO the candidatureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidatureDTO,
     * or with status {@code 400 (Bad Request)} if the candidatureDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the candidatureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CandidatureDTO> updateCandidature(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CandidatureDTO candidatureDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to update Candidature : {}, {}", id, candidatureDTO);
        if (candidatureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, candidatureDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!candidatureRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        candidatureDTO = candidatureService.update(candidatureDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, candidatureDTO.getId().toString()))
            .body(candidatureDTO);
    }

    /**
     * {@code PATCH  /candidatures/:id} : Partial updates given fields of an existing candidature, field will ignore if it is null
     *
     * @param id the id of the candidatureDTO to save.
     * @param candidatureDTO the candidatureDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated candidatureDTO,
     * or with status {@code 400 (Bad Request)} if the candidatureDTO is not valid,
     * or with status {@code 404 (Not Found)} if the candidatureDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the candidatureDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CandidatureDTO> partialUpdateCandidature(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CandidatureDTO candidatureDTO
    ) throws URISyntaxException {
        LOG.debug("REST request to partial update Candidature partially : {}, {}", id, candidatureDTO);
        if (candidatureDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, candidatureDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!candidatureRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CandidatureDTO> result = candidatureService.partialUpdate(candidatureDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, candidatureDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /candidatures} : get all the candidatures.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of candidatures in body.
     */
    @GetMapping("")
    public List<CandidatureDTO> getAllCandidatures() {
        LOG.debug("REST request to get all Candidatures");
        return candidatureService.findAll();
    }

    /**
     * {@code GET  /candidatures/:id} : get the "id" candidature.
     *
     * @param id the id of the candidatureDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the candidatureDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CandidatureDTO> getCandidature(@PathVariable("id") Long id) {
        LOG.debug("REST request to get Candidature : {}", id);
        Optional<CandidatureDTO> candidatureDTO = candidatureService.findOne(id);
        return ResponseUtil.wrapOrNotFound(candidatureDTO);
    }

    /**
     * {@code DELETE  /candidatures/:id} : delete the "id" candidature.
     *
     * @param id the id of the candidatureDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidature(@PathVariable("id") Long id) {
        LOG.debug("REST request to delete Candidature : {}", id);
        candidatureService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }

    //postuler

    @PostMapping("/postuler/{offreId}")
    public ResponseEntity<Candidature> postuler(@PathVariable Long offreId) {
        Optional<OffreEmploi> offreOpt = offreEmploiRepository.findById(offreId);
        if (offreOpt.isEmpty()) return ResponseEntity.notFound().build();

        Optional<User> user = userService.getUserWithAuthorities();
        if (user.isEmpty()) return ResponseEntity.status(403).build();

        Optional<Candidat> candidat = candidatRepository.findByUserId(user.get().getId());
        if (candidat.isEmpty()) return ResponseEntity.status(403).build();

        // Vérification : ne pas postuler 2 fois
        if (candidatureRepository.existsByCandidatIdAndOffreId(candidat.get().getId(), offreId)) {
            return ResponseEntity.badRequest().build();
        }

        Candidature candidature = new Candidature();
        candidature.setDateDepot(Instant.now());
        candidature.setStatut("En attente");
        candidature.setOffre(offreOpt.get());
        candidature.setCandidat(candidat.get());

        Candidature result = candidatureRepository.save(candidature);
        return ResponseEntity.ok(result);
    }

    //afficher les candidatures
    @GetMapping("/mes-candidatures")
    public ResponseEntity<List<Candidature>> getMesCandidatures() {
        Optional<User> user = userService.getUserWithAuthorities();
        if (user.isEmpty()) return ResponseEntity.status(403).build();

        Optional<Candidat> candidat = candidatRepository.findByUserId(user.get().getId());
        if (candidat.isEmpty()) return ResponseEntity.status(403).build();

        List<Candidature> result = candidatureRepository.findByCandidatIdWithOffre(candidat.get().getId());
        return ResponseEntity.ok(result);
    }
}
