package me.my_library_system.domain.reservation;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter(AccessLevel.PROTECTED)
@Getter
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookInfoId;
    private Long memberId;
    private ReservationStatus status;
    private LocalDateTime reservationDate;
    private LocalDateTime reservationCanceledDate;

    public static Reservation createReservation(Long bookInfoId, Long memberId) {
        Reservation reservation = new Reservation();
        reservation.bookInfoId = bookInfoId;
        reservation.memberId = memberId;
        reservation.reservationDate = LocalDateTime.now();
        reservation.status = ReservationStatus.ACTIVE;
        return reservation;
    }

    public void canceledReservation() {
        this.status = ReservationStatus.CANCELLED;
        this.reservationCanceledDate = LocalDateTime.now();
    }
}
