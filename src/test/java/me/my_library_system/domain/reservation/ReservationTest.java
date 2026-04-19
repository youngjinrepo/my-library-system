package me.my_library_system.domain.reservation;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ReservationTest {

    @Test
    void 예약_성공(){
        assertThat(ReservationFixture.normalReservation()).isNotNull();
    }


}