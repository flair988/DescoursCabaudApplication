package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MondayUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MondayUser.class);
        MondayUser mondayUser1 = new MondayUser();
        mondayUser1.setId(1L);
        MondayUser mondayUser2 = new MondayUser();
        mondayUser2.setId(mondayUser1.getId());
        assertThat(mondayUser1).isEqualTo(mondayUser2);
        mondayUser2.setId(2L);
        assertThat(mondayUser1).isNotEqualTo(mondayUser2);
        mondayUser1.setId(null);
        assertThat(mondayUser1).isNotEqualTo(mondayUser2);
    }
}
