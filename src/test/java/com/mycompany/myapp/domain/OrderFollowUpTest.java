package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderFollowUpTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderFollowUp.class);
        OrderFollowUp orderFollowUp1 = new OrderFollowUp();
        orderFollowUp1.setId(1L);
        OrderFollowUp orderFollowUp2 = new OrderFollowUp();
        orderFollowUp2.setId(orderFollowUp1.getId());
        assertThat(orderFollowUp1).isEqualTo(orderFollowUp2);
        orderFollowUp2.setId(2L);
        assertThat(orderFollowUp1).isNotEqualTo(orderFollowUp2);
        orderFollowUp1.setId(null);
        assertThat(orderFollowUp1).isNotEqualTo(orderFollowUp2);
    }
}
