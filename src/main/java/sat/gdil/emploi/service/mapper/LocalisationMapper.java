package sat.gdil.emploi.service.mapper;

import org.mapstruct.*;
import sat.gdil.emploi.domain.Localisation;
import sat.gdil.emploi.service.dto.LocalisationDTO;

/**
 * Mapper for the entity {@link Localisation} and its DTO {@link LocalisationDTO}.
 */
@Mapper(componentModel = "spring")
public interface LocalisationMapper extends EntityMapper<LocalisationDTO, Localisation> {}
