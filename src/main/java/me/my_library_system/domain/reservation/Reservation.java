package me.my_library_system.domain.reservation;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.my_library_system.domain.loan.exception.OverDueLoanException;
import me.my_library_system.domain.reservation.exception.DuplicatedReservationException;
import me.my_library_system.domain.reservation.exception.IllegalReservationStateException;
import me.my_library_system.domain.reservation.exception.ReservationCooldownException;
import me.my_library_system.domain.reservation.exception.ReservationLimitExceededException;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookInfoId;
    private Long memberId;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    private LocalDateTime reservationDate;
    private LocalDateTime reservationCanceledDate;

    public static Reservation createReservation(Long bookInfoId, Long memberId,
                                                ReservationPolicy policy, ReservationCreateContext context) {
        validate(policy, context);

        Reservation reservation = new Reservation();
        reservation.bookInfoId = bookInfoId;
        reservation.memberId = memberId;
        reservation.reservationDate = LocalDateTime.now();
        reservation.status = ReservationStatus.ACTIVE;
        return reservation;
    }

    private static void validate(ReservationPolicy policy, ReservationCreateContext context) {
        if (context.hasSameReserve()) {
            throw new DuplicatedReservationException();
        }
        if (context.reservationCnt() >= policy.maxReservationCnt()) {
            throw new ReservationLimitExceededException(policy.maxReservationCnt());
        }
        if (context.cancellationCnt() > 0) {
            throw new ReservationCooldownException(policy.cancellationCooldown());
        }
        if (context.overdueCnt() > 0) {
            throw new OverDueLoanException("연체중인 도서가 있어 예약 할 수 없습니다.");

        }
    }

    public void canceledReservation() {
        if (this.status != ReservationStatus.ACTIVE) {
            throw new IllegalReservationStateException(this.status, "취소");
        }
        this.status = ReservationStatus.CANCELLED;
        this.reservationCanceledDate = LocalDateTime.now();
    }
}
