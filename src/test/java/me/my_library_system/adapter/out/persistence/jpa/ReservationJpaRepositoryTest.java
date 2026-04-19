package me.my_library_system.adapter.out.persistence.jpa;

import me.my_library_system.domain.reservation.Reservation;
import me.my_library_system.domain.reservation.ReservationFixture;
import me.my_library_system.domain.reservation.ReservationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Transactional
class ReservationJpaRepositoryTest {

    @Autowired
    private ReservationJpaRepository reservationJpaRepository;

    @Test
    void 예약_내역_조회_서비스_정상(){
        reservationJpaRepository.save(ReservationFixture.normalReservation());

        List<Reservation> reservations = reservationJpaRepository.findByMemberIdAndStatus(1L, ReservationStatus.ACTIVE);
        assertThat(reservations).hasSize(1);
    }

    @Test
    void 예약_취소_내역_조회( ){
        reservationJpaRepository.save(ReservationFixture.canceledReservation());

        int cancelCount = reservationJpaRepository.countByMemberId(1L, ReservationStatus.CANCELLED, LocalDateTime.MAX, LocalDateTime.MIN);
        assertThat(cancelCount).isEqualTo(1);
    }
}