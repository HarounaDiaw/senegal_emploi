package sat.gdil.emploi.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sat.gdil.emploi.domain.Localisation;
import sat.gdil.emploi.repository.LocalisationRepository;
import sat.gdil.emploi.service.LocalisationService;
import sat.gdil.emploi.service.dto.LocalisationDTO;
import sat.gdil.emploi.service.mapper.LocalisationMapper;

/**
 * Service Implementation for managing {@link sat.gdil.emploi.domain.Localisation}.
 */
@Service
@Transactional
public class LocalisationServiceImpl implements LocalisationService {

    private static final Logger LOG = LoggerFactory.getLogger(LocalisationServiceImpl.class);

    private final LocalisationRepository localisationRepository;

    private final LocalisationMapper localisationMapper;

    public LocalisationServiceImpl(LocalisationRepository localisationRepository, LocalisationMapper localisationMapper) {
        this.localisationRepository = localisationRepository;
        this.localisationMapper = localisationMapper;
    }

    @Override
    public LocalisationDTO save(LocalisationDTO localisationDTO) {
        LOG.debug("Request to save Localisation : {}", localisationDTO);
        Localisation localisation = localisationMapper.toEntity(localisationDTO);
        localisation = localisationRepository.save(localisation);
        return localisationMapper.toDto(localisation);
    }

    @Override
    public LocalisationDTO update(LocalisationDTO localisationDTO) {
        LOG.debug("Request to update Localisation : {}", localisationDTO);
        Localisation localisation = localisationMapper.toEntity(localisationDTO);
        localisation = localisationRepository.save(localisation);
        return localisationMapper.toDto(localisation);
    }

    @Override
    public Optional<LocalisationDTO> partialUpdate(LocalisationDTO localisationDTO) {
        LOG.debug("Request to partially update Localisation : {}", localisationDTO);

        return localisationRepository
            .findById(localisationDTO.getId())
            .map(existingLocalisation -> {
                localisationMapper.partialUpdate(existingLocalisation, localisationDTO);

                return existingLocalisation;
            })
            .map(localisationRepository::save)
            .map(localisationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocalisationDTO> findAll() {
        LOG.debug("Request to get all Localisations");
        return localisationRepository.findAll().stream().map(localisationMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LocalisationDTO> findOne(Long id) {
        LOG.debug("Request to get Localisation : {}", id);
        return localisationRepository.findById(id).map(localisationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Localisation : {}", id);
        localisationRepository.deleteById(id);
    }
}
