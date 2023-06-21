package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductTaxmonomyTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductTaxmonomy.class);
        ProductTaxmonomy productTaxmonomy1 = new ProductTaxmonomy();
        productTaxmonomy1.setId(1L);
        ProductTaxmonomy productTaxmonomy2 = new ProductTaxmonomy();
        productTaxmonomy2.setId(productTaxmonomy1.getId());
        assertThat(productTaxmonomy1).isEqualTo(productTaxmonomy2);
        productTaxmonomy2.setId(2L);
        assertThat(productTaxmonomy1).isNotEqualTo(productTaxmonomy2);
        productTaxmonomy1.setId(null);
        assertThat(productTaxmonomy1).isNotEqualTo(productTaxmonomy2);
    }
}
