package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ForwarderTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Forwarder.class);
        Forwarder forwarder1 = new Forwarder();
        forwarder1.setId(1L);
        Forwarder forwarder2 = new Forwarder();
        forwarder2.setId(forwarder1.getId());
        assertThat(forwarder1).isEqualTo(forwarder2);
        forwarder2.setId(2L);
        assertThat(forwarder1).isNotEqualTo(forwarder2);
        forwarder1.setId(null);
        assertThat(forwarder1).isNotEqualTo(forwarder2);
    }
}
