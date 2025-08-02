package sat.gdil.emploi.service.mapper;

import org.mapstruct.*;
import sat.gdil.emploi.domain.Localisation;
import sat.gdil.emploi.domain.OffreEmploi;
import sat.gdil.emploi.domain.Poste;
import sat.gdil.emploi.domain.Recruteur;
import sat.gdil.emploi.domain.TypeContrat;
import sat.gdil.emploi.service.dto.LocalisationDTO;
import sat.gdil.emploi.service.dto.OffreEmploiDTO;
import sat.gdil.emploi.service.dto.PosteDTO;
import sat.gdil.emploi.service.dto.RecruteurDTO;
import sat.gdil.emploi.service.dto.TypeContratDTO;

/**
 * Mapper for the entity {@link OffreEmploi} and its DTO {@link OffreEmploiDTO}.
 */
@Mapper(componentModel = "spring")
public interface OffreEmploiMapper extends EntityMapper<OffreEmploiDTO, OffreEmploi> {
    @Mapping(target = "recruteur", source = "recruteur", qualifiedByName = "recruteurId")
    @Mapping(target = "typeContrat", source = "typeContrat", qualifiedByName = "typeContratId")
    @Mapping(target = "poste", source = "poste", qualifiedByName = "posteId")
    @Mapping(target = "localisation", source = "localisation", qualifiedByName = "localisationId")
    OffreEmploiDTO toDto(OffreEmploi s);

    @Named("recruteurId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    RecruteurDTO toDtoRecruteurId(Recruteur recruteur);

    @Named("typeContratId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TypeContratDTO toDtoTypeContratId(TypeContrat typeContrat);

    @Named("posteId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PosteDTO toDtoPosteId(Poste poste);

    @Named("localisationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LocalisationDTO toDtoLocalisationId(Localisation localisation);

    @Override
    @Mapping(target = "recruteur.user", ignore = true)
    OffreEmploi toEntity(OffreEmploiDTO dto);

    @Override
    @Mapping(target = "recruteur.user", ignore = true)
    void partialUpdate(@MappingTarget OffreEmploi entity, OffreEmploiDTO dto);
}
