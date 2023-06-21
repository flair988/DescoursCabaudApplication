package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OperationSiteTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperationSite.class);
        OperationSite operationSite1 = new OperationSite();
        operationSite1.setId(1L);
        OperationSite operationSite2 = new OperationSite();
        operationSite2.setId(operationSite1.getId());
        assertThat(operationSite1).isEqualTo(operationSite2);
        operationSite2.setId(2L);
        assertThat(operationSite1).isNotEqualTo(operationSite2);
        operationSite1.setId(null);
        assertThat(operationSite1).isNotEqualTo(operationSite2);
    }
}
