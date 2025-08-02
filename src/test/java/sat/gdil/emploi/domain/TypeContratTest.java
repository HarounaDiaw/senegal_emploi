package sat.gdil.emploi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static sat.gdil.emploi.domain.TypeContratTestSamples.*;

import org.junit.jupiter.api.Test;
import sat.gdil.emploi.web.rest.TestUtil;

class TypeContratTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TypeContrat.class);
        TypeContrat typeContrat1 = getTypeContratSample1();
        TypeContrat typeContrat2 = new TypeContrat();
        assertThat(typeContrat1).isNotEqualTo(typeContrat2);

        typeContrat2.setId(typeContrat1.getId());
        assertThat(typeContrat1).isEqualTo(typeContrat2);

        typeContrat2 = getTypeContratSample2();
        assertThat(typeContrat1).isNotEqualTo(typeContrat2);
    }
}
