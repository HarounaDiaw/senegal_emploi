package sat.gdil.emploi.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sat.gdil.emploi.domain.OffreEmploi;
import sat.gdil.emploi.repository.OffreEmploiRepository;
import sat.gdil.emploi.service.OffreEmploiService;
import sat.gdil.emploi.service.dto.OffreEmploiDTO;
import sat.gdil.emploi.service.mapper.OffreEmploiMapper;

/**
 * Service Implementation for managing {@link sat.gdil.emploi.domain.OffreEmploi}.
 */
@Service
@Transactional
public class OffreEmploiServiceImpl implements OffreEmploiService {

    private static final Logger LOG = LoggerFactory.getLogger(OffreEmploiServiceImpl.class);

    private final OffreEmploiRepository offreEmploiRepository;

    private final OffreEmploiMapper offreEmploiMapper;

    public OffreEmploiServiceImpl(OffreEmploiRepository offreEmploiRepository, OffreEmploiMapper offreEmploiMapper) {
        this.offreEmploiRepository = offreEmploiRepository;
        this.offreEmploiMapper = offreEmploiMapper;
    }

    @Override
    public OffreEmploiDTO save(OffreEmploiDTO offreEmploiDTO) {
        LOG.debug("Request to save OffreEmploi : {}", offreEmploiDTO);
        OffreEmploi offreEmploi = offreEmploiMapper.toEntity(offreEmploiDTO);
        offreEmploi = offreEmploiRepository.save(offreEmploi);
        return offreEmploiMapper.toDto(offreEmploi);
    }

    @Override
    public OffreEmploiDTO update(OffreEmploiDTO offreEmploiDTO) {
        LOG.debug("Request to update OffreEmploi : {}", offreEmploiDTO);
        OffreEmploi offreEmploi = offreEmploiMapper.toEntity(offreEmploiDTO);
        offreEmploi = offreEmploiRepository.save(offreEmploi);
        return offreEmploiMapper.toDto(offreEmploi);
    }

    @Override
    public Optional<OffreEmploiDTO> partialUpdate(OffreEmploiDTO offreEmploiDTO) {
        LOG.debug("Request to partially update OffreEmploi : {}", offreEmploiDTO);

        return offreEmploiRepository
            .findById(offreEmploiDTO.getId())
            .map(existingOffreEmploi -> {
                offreEmploiMapper.partialUpdate(existingOffreEmploi, offreEmploiDTO);

                return existingOffreEmploi;
            })
            .map(offreEmploiRepository::save)
            .map(offreEmploiMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OffreEmploiDTO> findAll() {
        LOG.debug("Request to get all OffreEmplois");
        return offreEmploiRepository.findAll().stream().map(offreEmploiMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OffreEmploiDTO> findOne(Long id) {
        LOG.debug("Request to get OffreEmploi : {}", id);
        return offreEmploiRepository.findById(id).map(offreEmploiMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete OffreEmploi : {}", id);
        offreEmploiRepository.deleteById(id);
    }
}
