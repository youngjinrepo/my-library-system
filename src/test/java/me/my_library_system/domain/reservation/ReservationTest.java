package me.my_library_system.domain.reservation;

import me.my_library_system.domain.loan.exception.OverDueLoanException;
import me.my_library_system.domain.reservation.exception.DuplicatedReservationException;
import me.my_library_system.domain.reservation.exception.ReservationCooldownException;
import me.my_library_system.domain.reservation.exception.ReservationLimitExceededException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ReservationTest {

    private final ReservationPolicy policy = new ReservationPolicy(5,3);

    @Nested
    class createReservationTests {
        @Test
        void success() {
            ReservationCreateContext context = new ReservationCreateContext(
                    false, 0, 0 ,0);

            Reservation reservation = Reservation.createReservation(1L, 10L, policy,context);

            assertThat(reservation.getBookInfoId()).isEqualTo(1L);
            assertThat(reservation.getMemberId()).isEqualTo(10L);
            assertThat(reservation.getReservationDate()).isNotNull();
            assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.ACTIVE);
        }

        @Test
        void 동일_도서_예약_실패() {
            ReservationCreateContext context = new ReservationCreateContext(
                    true, 0, 0 ,0);
            assertThatThrownBy(
                    () -> Reservation.createReservation(1L, 10L, policy,context)).
                    isInstanceOf(DuplicatedReservationException.class);
        }

        @Test
        void 최대_예약_건수_이상_실패() {
            ReservationCreateContext context = new ReservationCreateContext(
                    false, policy.maxReservationCnt(), 0 ,0);
            assertThatThrownBy(
                    () -> Reservation.createReservation(1L, 10L, policy,context))
                    .isInstanceOf(ReservationLimitExceededException.class);
        }

        @Test
        void 예약_취소시_일정_기간동안_예약_불가() {
            ReservationCreateContext context = new ReservationCreateContext(
                    false, 0, 1 ,0);
            assertThatThrownBy(
                    () -> Reservation.createReservation(1L, 10L, policy,context))
                    .isInstanceOf(ReservationCooldownException.class);
        }

        @Test
        void 연체중에는_예약_불가() {
            ReservationCreateContext context = new ReservationCreateContext(
                    false, 0, 0 ,1);
            assertThatThrownBy(
                    ()->Reservation.createReservation(1L, 10L, policy,context))
                    .isInstanceOf(OverDueLoanException.class);

        }
        
    }

    @Nested
    class cancelReservationTests {
        @Test
        void 예약_취소() {
            ReservationCreateContext context = new ReservationCreateContext(
                    false, 0, 0 ,0);

            Reservation reservation = Reservation.createReservation(1L, 10L, policy,context);
            reservation.canceledReservation();

            Assertions.assertThat(reservation.getStatus()).isEqualTo(ReservationStatus.CANCELLED);
            Assertions.assertThat(reservation.getReservationCanceledDate()).isNotNull();
        }
    }
}