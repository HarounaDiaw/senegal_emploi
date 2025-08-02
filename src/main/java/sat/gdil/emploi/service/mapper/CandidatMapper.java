package sat.gdil.emploi.service.mapper;

import org.mapstruct.*;
import sat.gdil.emploi.domain.Candidat;
import sat.gdil.emploi.service.dto.CandidatDTO;

/**
 * Mapper for the entity {@link Candidat} and its DTO {@link CandidatDTO}.
 */
@Mapper(componentModel = "spring")
public interface CandidatMapper extends EntityMapper<CandidatDTO, Candidat> {
    @Override
    @Mapping(target = "user", ignore = true)
    Candidat toEntity(CandidatDTO dto);

    @Override
    @Mapping(target = "user", ignore = true)
    void partialUpdate(@MappingTarget Candidat entity, CandidatDTO dto);
}
