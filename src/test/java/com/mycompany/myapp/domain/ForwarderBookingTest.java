package com.mycompany.myapp.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.mycompany.myapp.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ForwarderBookingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ForwarderBooking.class);
        ForwarderBooking forwarderBooking1 = new ForwarderBooking();
        forwarderBooking1.setId(1L);
        ForwarderBooking forwarderBooking2 = new ForwarderBooking();
        forwarderBooking2.setId(forwarderBooking1.getId());
        assertThat(forwarderBooking1).isEqualTo(forwarderBooking2);
        forwarderBooking2.setId(2L);
        assertThat(forwarderBooking1).isNotEqualTo(forwarderBooking2);
        forwarderBooking1.setId(null);
        assertThat(forwarderBooking1).isNotEqualTo(forwarderBooking2);
    }
}
