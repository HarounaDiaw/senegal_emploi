package sat.gdil.emploi.service.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sat.gdil.emploi.domain.TypeContrat;
import sat.gdil.emploi.repository.TypeContratRepository;
import sat.gdil.emploi.service.TypeContratService;
import sat.gdil.emploi.service.dto.TypeContratDTO;
import sat.gdil.emploi.service.mapper.TypeContratMapper;

/**
 * Service Implementation for managing {@link sat.gdil.emploi.domain.TypeContrat}.
 */
@Service
@Transactional
public class TypeContratServiceImpl implements TypeContratService {

    private static final Logger LOG = LoggerFactory.getLogger(TypeContratServiceImpl.class);

    private final TypeContratRepository typeContratRepository;

    private final TypeContratMapper typeContratMapper;

    public TypeContratServiceImpl(TypeContratRepository typeContratRepository, TypeContratMapper typeContratMapper) {
        this.typeContratRepository = typeContratRepository;
        this.typeContratMapper = typeContratMapper;
    }

    @Override
    public TypeContratDTO save(TypeContratDTO typeContratDTO) {
        LOG.debug("Request to save TypeContrat : {}", typeContratDTO);
        TypeContrat typeContrat = typeContratMapper.toEntity(typeContratDTO);
        typeContrat = typeContratRepository.save(typeContrat);
        return typeContratMapper.toDto(typeContrat);
    }

    @Override
    public TypeContratDTO update(TypeContratDTO typeContratDTO) {
        LOG.debug("Request to update TypeContrat : {}", typeContratDTO);
        TypeContrat typeContrat = typeContratMapper.toEntity(typeContratDTO);
        typeContrat = typeContratRepository.save(typeContrat);
        return typeContratMapper.toDto(typeContrat);
    }

    @Override
    public Optional<TypeContratDTO> partialUpdate(TypeContratDTO typeContratDTO) {
        LOG.debug("Request to partially update TypeContrat : {}", typeContratDTO);

        return typeContratRepository
            .findById(typeContratDTO.getId())
            .map(existingTypeContrat -> {
                typeContratMapper.partialUpdate(existingTypeContrat, typeContratDTO);

                return existingTypeContrat;
            })
            .map(typeContratRepository::save)
            .map(typeContratMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TypeContratDTO> findAll() {
        LOG.debug("Request to get all TypeContrats");
        return typeContratRepository.findAll().stream().map(typeContratMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TypeContratDTO> findOne(Long id) {
        LOG.debug("Request to get TypeContrat : {}", id);
        return typeContratRepository.findById(id).map(typeContratMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        LOG.debug("Request to delete TypeContrat : {}", id);
        typeContratRepository.deleteById(id);
    }
}
