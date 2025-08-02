package sat.gdil.emploi.service.mapper;

import static sat.gdil.emploi.domain.LocalisationAsserts.*;
import static sat.gdil.emploi.domain.LocalisationTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LocalisationMapperTest {

    private LocalisationMapper localisationMapper;

    @BeforeEach
    void setUp() {
        localisationMapper = new LocalisationMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getLocalisationSample1();
        var actual = localisationMapper.toEntity(localisationMapper.toDto(expected));
        assertLocalisationAllPropertiesEquals(expected, actual);
    }
}
