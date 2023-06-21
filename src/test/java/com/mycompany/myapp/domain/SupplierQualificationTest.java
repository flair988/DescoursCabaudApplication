package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SupplierQualificationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SupplierQualification.class);
        SupplierQualification supplierQualification1 = new SupplierQualification();
        supplierQualification1.setId(1L);
        SupplierQualification supplierQualification2 = new SupplierQualification();
        supplierQualification2.setId(supplierQualification1.getId());
        assertThat(supplierQualification1).isEqualTo(supplierQualification2);
        supplierQualification2.setId(2L);
        assertThat(supplierQualification1).isNotEqualTo(supplierQualification2);
        supplierQualification1.setId(null);
        assertThat(supplierQualification1).isNotEqualTo(supplierQualification2);
    }
}
