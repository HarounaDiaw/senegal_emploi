package sat.gdil.emploi.service.mapper;

import org.mapstruct.*;
import sat.gdil.emploi.domain.Candidat;
import sat.gdil.emploi.domain.Candidature;
import sat.gdil.emploi.domain.OffreEmploi;
import sat.gdil.emploi.service.dto.CandidatDTO;
import sat.gdil.emploi.service.dto.CandidatureDTO;
import sat.gdil.emploi.service.dto.OffreEmploiDTO;

/**
 * Mapper for the entity {@link Candidature} and its DTO {@link CandidatureDTO}.
 */
@Mapper(componentModel = "spring")
public interface CandidatureMapper extends EntityMapper<CandidatureDTO, Candidature> {
    @Mapping(target = "offre", source = "offre", qualifiedByName = "offreEmploiId")
    @Mapping(target = "candidat", source = "candidat", qualifiedByName = "candidatId")
    CandidatureDTO toDto(Candidature s);

    @Named("offreEmploiId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OffreEmploiDTO toDtoOffreEmploiId(OffreEmploi offreEmploi);

    @Named("candidatId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CandidatDTO toDtoCandidatId(Candidat candidat);

    @Override
    @Mapping(target = "candidat.user", ignore = true)
    @Mapping(target = "offre.recruteur.user", ignore = true)
    Candidature toEntity(CandidatureDTO dto);

    @Override
    @Mapping(target = "candidat.user", ignore = true)
    @Mapping(target = "offre.recruteur.user", ignore = true)
    void partialUpdate(@MappingTarget Candidature entity, CandidatureDTO dto);
}
