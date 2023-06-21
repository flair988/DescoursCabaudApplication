package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ProductFinishedTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ProductFinished.class);
        ProductFinished productFinished1 = new ProductFinished();
        productFinished1.setId(1L);
        ProductFinished productFinished2 = new ProductFinished();
        productFinished2.setId(productFinished1.getId());
        assertThat(productFinished1).isEqualTo(productFinished2);
        productFinished2.setId(2L);
        assertThat(productFinished1).isNotEqualTo(productFinished2);
        productFinished1.setId(null);
        assertThat(productFinished1).isNotEqualTo(productFinished2);
    }
}
