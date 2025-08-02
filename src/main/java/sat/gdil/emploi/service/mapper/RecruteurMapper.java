package sat.gdil.emploi.service.mapper;

import org.mapstruct.*;
import sat.gdil.emploi.domain.Recruteur;
import sat.gdil.emploi.service.dto.RecruteurDTO;

/**
 * Mapper for the entity {@link Recruteur} and its DTO {@link RecruteurDTO}.
 */
@Mapper(componentModel = "spring")
public interface RecruteurMapper extends EntityMapper<RecruteurDTO, Recruteur> {
    @Override
    @Mapping(target = "user", ignore = true)
    Recruteur toEntity(RecruteurDTO dto);

    @Override
    @Mapping(target = "user", ignore = true)
    void partialUpdate(@MappingTarget Recruteur entity, RecruteurDTO dto);
}
