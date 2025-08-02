package sat.gdil.emploi.service.mapper;

import static sat.gdil.emploi.domain.PosteAsserts.*;
import static sat.gdil.emploi.domain.PosteTestSamples.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PosteMapperTest {

    private PosteMapper posteMapper;

    @BeforeEach
    void setUp() {
        posteMapper = new PosteMapperImpl();
    }

    @Test
    void shouldConvertToDtoAndBack() {
        var expected = getPosteSample1();
        var actual = posteMapper.toEntity(posteMapper.toDto(expected));
        assertPosteAllPropertiesEquals(expected, actual);
    }
}
