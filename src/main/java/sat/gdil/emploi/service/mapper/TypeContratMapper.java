package sat.gdil.emploi.service.mapper;

import org.mapstruct.*;
import sat.gdil.emploi.domain.TypeContrat;
import sat.gdil.emploi.service.dto.TypeContratDTO;

/**
 * Mapper for the entity {@link TypeContrat} and its DTO {@link TypeContratDTO}.
 */
@Mapper(componentModel = "spring")
public interface TypeContratMapper extends EntityMapper<TypeContratDTO, TypeContrat> {}
