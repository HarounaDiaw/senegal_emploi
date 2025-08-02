package sat.gdil.emploi.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sat.gdil.emploi.domain.Candidature;
import sat.gdil.emploi.repository.CandidatureRepository;
import sat.gdil.emploi.service.CandidatureService;
import sat.gdil.emploi.service.dto.CandidatureDTO;
import sat.gdil.emploi.service.mapper.CandidatureMapper;

/**
 * Service Implementation for managing {@link sat.gdil.emploi.domain.Candidature}.
 */
@Service
@Transactional
public class CandidatureServiceImpl implements CandidatureService {

    private static final Logger LOG = LoggerFactory.getLogger(CandidatureServiceImpl.class);

    private final CandidatureRepository candidatureRepository;

    private final CandidatureMapper candidatureMapper;

    public CandidatureServiceImpl(CandidatureRepository candidatureRepository, CandidatureMapper candidatureMapper) {
        this.candidatureRepository = candidatureRepository;
        this.candidatureMapper = candidatureMapper;
    }

    @Override
    public CandidatureDTO save(CandidatureDTO candidatureDTO) {
        LOG.debug("Request to save Candidature : {}", candidatureDTO);
        Candidature candidature = candidatureMapper.toEntity(candidatureDTO);
        candidature = candidatureRepository.save(candidature);
        return candidatureMapper.toDto(candidature);
    }

    @Override
    public CandidatureDTO update(CandidatureDTO candidatureDTO) {
        LOG.debug("Request to update Candidature : {}", candidatureDTO);
        Candidature candidature = candidatureMapper.toEntity(candidatureDTO);
        candidature = candidatureRepository.save(candidature);
        return candidatureMapper.toDto(candidature);
    }

    @Override
    public Optional<CandidatureDTO> partialUpdate(CandidatureDTO candidatureDTO) {
        LOG.debug("Request to partially update Candidature : {}", candidatureDTO);

        return candidatureRepository
            .findById(candidatureDTO.getId())
            .map(existingCandidature -> {
                candidatureMapper.partialUpdate(existingCandidature, candidatureDTO);

                return existingCandidature;
            })
            .map(candidatureRepository::save)
            .map(candidatureMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CandidatureDTO> findAll() {
        LOG.debug("Request to get all Candidatures");
        return candidatureRepository.findAll().stream().map(candidatureMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CandidatureDTO> findOne(Long id) {
        LOG.debug("Request to get Candidature : {}", id);
        return candidatureRepository.findById(id).map(candidatureMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Candidature : {}", id);
        candidatureRepository.deleteById(id);
    }
}
