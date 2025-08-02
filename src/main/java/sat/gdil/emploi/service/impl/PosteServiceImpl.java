package sat.gdil.emploi.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sat.gdil.emploi.domain.Poste;
import sat.gdil.emploi.repository.PosteRepository;
import sat.gdil.emploi.service.PosteService;
import sat.gdil.emploi.service.dto.PosteDTO;
import sat.gdil.emploi.service.mapper.PosteMapper;

/**
 * Service Implementation for managing {@link sat.gdil.emploi.domain.Poste}.
 */
@Service
@Transactional
public class PosteServiceImpl implements PosteService {

    private static final Logger LOG = LoggerFactory.getLogger(PosteServiceImpl.class);

    private final PosteRepository posteRepository;

    private final PosteMapper posteMapper;

    public PosteServiceImpl(PosteRepository posteRepository, PosteMapper posteMapper) {
        this.posteRepository = posteRepository;
        this.posteMapper = posteMapper;
    }

    @Override
    public PosteDTO save(PosteDTO posteDTO) {
        LOG.debug("Request to save Poste : {}", posteDTO);
        Poste poste = posteMapper.toEntity(posteDTO);
        poste = posteRepository.save(poste);
        return posteMapper.toDto(poste);
    }

    @Override
    public PosteDTO update(PosteDTO posteDTO) {
        LOG.debug("Request to update Poste : {}", posteDTO);
        Poste poste = posteMapper.toEntity(posteDTO);
        poste = posteRepository.save(poste);
        return posteMapper.toDto(poste);
    }

    @Override
    public Optional<PosteDTO> partialUpdate(PosteDTO posteDTO) {
        LOG.debug("Request to partially update Poste : {}", posteDTO);

        return posteRepository
            .findById(posteDTO.getId())
            .map(existingPoste -> {
                posteMapper.partialUpdate(existingPoste, posteDTO);

                return existingPoste;
            })
            .map(posteRepository::save)
            .map(posteMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PosteDTO> findAll() {
        LOG.debug("Request to get all Postes");
        return posteRepository.findAll().stream().map(posteMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PosteDTO> findOne(Long id) {
        LOG.debug("Request to get Poste : {}", id);
        return posteRepository.findById(id).map(posteMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete Poste : {}", id);
        posteRepository.deleteById(id);
    }
}
