package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class MondayColumnTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MondayColumn.class);
        MondayColumn mondayColumn1 = new MondayColumn();
        mondayColumn1.setId(1L);
        MondayColumn mondayColumn2 = new MondayColumn();
        mondayColumn2.setId(mondayColumn1.getId());
        assertThat(mondayColumn1).isEqualTo(mondayColumn2);
        mondayColumn2.setId(2L);
        assertThat(mondayColumn1).isNotEqualTo(mondayColumn2);
        mondayColumn1.setId(null);
        assertThat(mondayColumn1).isNotEqualTo(mondayColumn2);
    }
}
