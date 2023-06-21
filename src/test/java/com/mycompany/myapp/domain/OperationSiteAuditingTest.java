package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OperationSiteAuditingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OperationSiteAuditing.class);
        OperationSiteAuditing operationSiteAuditing1 = new OperationSiteAuditing();
        operationSiteAuditing1.setId(1L);
        OperationSiteAuditing operationSiteAuditing2 = new OperationSiteAuditing();
        operationSiteAuditing2.setId(operationSiteAuditing1.getId());
        assertThat(operationSiteAuditing1).isEqualTo(operationSiteAuditing2);
        operationSiteAuditing2.setId(2L);
        assertThat(operationSiteAuditing1).isNotEqualTo(operationSiteAuditing2);
        operationSiteAuditing1.setId(null);
        assertThat(operationSiteAuditing1).isNotEqualTo(operationSiteAuditing2);
    }
}
