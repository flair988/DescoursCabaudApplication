package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesDeliveryTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesDelivery.class);
        SalesDelivery salesDelivery1 = new SalesDelivery();
        salesDelivery1.setId(1L);
        SalesDelivery salesDelivery2 = new SalesDelivery();
        salesDelivery2.setId(salesDelivery1.getId());
        assertThat(salesDelivery1).isEqualTo(salesDelivery2);
        salesDelivery2.setId(2L);
        assertThat(salesDelivery1).isNotEqualTo(salesDelivery2);
        salesDelivery1.setId(null);
        assertThat(salesDelivery1).isNotEqualTo(salesDelivery2);
    }
}
