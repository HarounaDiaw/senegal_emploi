package sat.gdil.emploi.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static sat.gdil.emploi.domain.RecruteurTestSamples.*;

import org.junit.jupiter.api.Test;
import sat.gdil.emploi.web.rest.TestUtil;

class RecruteurTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Recruteur.class);
        Recruteur recruteur1 = getRecruteurSample1();
        Recruteur recruteur2 = new Recruteur();
        assertThat(recruteur1).isNotEqualTo(recruteur2);

        recruteur2.setId(recruteur1.getId());
        assertThat(recruteur1).isEqualTo(recruteur2);

        recruteur2 = getRecruteurSample2();
        assertThat(recruteur1).isNotEqualTo(recruteur2);
    }
}
