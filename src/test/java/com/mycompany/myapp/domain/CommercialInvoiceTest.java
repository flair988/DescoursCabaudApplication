package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class CommercialInvoiceTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(CommercialInvoice.class);
        CommercialInvoice commercialInvoice1 = new CommercialInvoice();
        commercialInvoice1.setId(1L);
        CommercialInvoice commercialInvoice2 = new CommercialInvoice();
        commercialInvoice2.setId(commercialInvoice1.getId());
        assertThat(commercialInvoice1).isEqualTo(commercialInvoice2);
        commercialInvoice2.setId(2L);
        assertThat(commercialInvoice1).isNotEqualTo(commercialInvoice2);
        commercialInvoice1.setId(null);
        assertThat(commercialInvoice1).isNotEqualTo(commercialInvoice2);
    }
}
