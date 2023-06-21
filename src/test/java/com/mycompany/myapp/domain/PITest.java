package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PITest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PI.class);
        PI pI1 = new PI();
        pI1.setId(1L);
        PI pI2 = new PI();
        pI2.setId(pI1.getId());
        assertThat(pI1).isEqualTo(pI2);
        pI2.setId(2L);
        assertThat(pI1).isNotEqualTo(pI2);
        pI1.setId(null);
        assertThat(pI1).isNotEqualTo(pI2);
    }
}
