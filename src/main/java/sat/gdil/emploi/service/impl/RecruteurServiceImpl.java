package sat.gdil.emploi.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sat.gdil.emploi.domain.Recruteur;
import sat.gdil.emploi.repository.RecruteurRepository;
import sat.gdil.emploi.service.RecruteurService;
import sat.gdil.emploi.service.dto.RecruteurDTO;
import sat.gdil.emploi.service.mapper.RecruteurMapper;

/**
 * Service Implementation for managing {@link sat.gdil.emploi.domain.Recruteur}.
 */
@Service
@Transactional
public class RecruteurServiceImpl implements RecruteurService {

    private static final Logger LOG = LoggerFactory.getLogger(RecruteurServiceImpl.class);

    private final RecruteurRepository recruteurRepository;

    private final RecruteurMapper recruteurMapper;

    public RecruteurServiceImpl(RecruteurRepository recruteurRepository, RecruteurMapper recruteurMapper) {
        this.recruteurRepository = recruteurRepository;
        this.recruteurMapper = recruteurMapper;
    }

    @Override
    public RecruteurDTO save(RecruteurDTO recruteurDTO) {
        LOG.debug("Request to save Recruteur : {}", recruteurDTO);
        Recruteur recruteur = recruteurMapper.toEntity(recruteurDTO);
        recruteur = recruteurRepository.save(recruteur);
        return recruteurMapper.toDto(recruteur);
    }

    @Override
    public RecruteurDTO update(RecruteurDTO recruteurDTO) {
        LOG.debug("Request to update Recruteur : {}", recruteurDTO);
        Recruteur recruteur = recruteurMapper.toEntity(recruteurDTO);
        recruteur = recruteurRepository.save(recruteur);
        return recruteurMapper.toDto(recruteur);
    }

    @Override
    public Optional<RecruteurDTO> partialUpdate(RecruteurDTO recruteurDTO) {
        LOG.debug("Request to partially update Recruteur : {}", recruteurDTO);

        return recruteurRepository
            .findById(recruteurDTO.getId())
            .map(existingRecruteur -> {
                recruteurMapper.partialUpdate(existingRecruteur, recruteurDTO);

                return existingRecruteur;
            })
            .map(recruteurRepository::save)
            .map(recruteurMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecruteurDTO> findAll() {
        LOG.debug("Request to get all Recruteurs");
        return recruteurRepository.findAll().stream().map(recruteurMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RecruteurDTO> findOne(Long id) {
        LOG.debug("Request to get Recruteur : {}", id);
        return recruteurRepository.findById(id).map(recruteurMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Recruteur : {}", id);
        recruteurRepository.deleteById(id);
    }
}
