package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class PurchaseReturnTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PurchaseReturn.class);
        PurchaseReturn purchaseReturn1 = new PurchaseReturn();
        purchaseReturn1.setId(1L);
        PurchaseReturn purchaseReturn2 = new PurchaseReturn();
        purchaseReturn2.setId(purchaseReturn1.getId());
        assertThat(purchaseReturn1).isEqualTo(purchaseReturn2);
        purchaseReturn2.setId(2L);
        assertThat(purchaseReturn1).isNotEqualTo(purchaseReturn2);
        purchaseReturn1.setId(null);
        assertThat(purchaseReturn1).isNotEqualTo(purchaseReturn2);
    }
}
