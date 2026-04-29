package me.my_library_system.domain.reservation;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookInfoId;
    private Long memberId;
    @Enumerated(EnumType.STRING)
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
