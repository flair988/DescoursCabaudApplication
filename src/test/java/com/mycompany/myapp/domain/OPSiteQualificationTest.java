package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OPSiteQualificationTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OPSiteQualification.class);
        OPSiteQualification oPSiteQualification1 = new OPSiteQualification();
        oPSiteQualification1.setId(1L);
        OPSiteQualification oPSiteQualification2 = new OPSiteQualification();
        oPSiteQualification2.setId(oPSiteQualification1.getId());
        assertThat(oPSiteQualification1).isEqualTo(oPSiteQualification2);
        oPSiteQualification2.setId(2L);
        assertThat(oPSiteQualification1).isNotEqualTo(oPSiteQualification2);
        oPSiteQualification1.setId(null);
        assertThat(oPSiteQualification1).isNotEqualTo(oPSiteQualification2);
    }
}
