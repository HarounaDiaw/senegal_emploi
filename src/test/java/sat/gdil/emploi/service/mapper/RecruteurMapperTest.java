package sat.gdil.emploi.service.mapper;

import static sat.gdil.emploi.domain.RecruteurAsserts.*;
import static sat.gdil.emploi.domain.RecruteurTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecruteurMapperTest {

    private RecruteurMapper recruteurMapper;

    @BeforeEach
    void setUp() {
        recruteurMapper = new RecruteurMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getRecruteurSample1();
        var actual = recruteurMapper.toEntity(recruteurMapper.toDto(expected));
        assertRecruteurAllPropertiesEquals(expected, actual);
    }
}
