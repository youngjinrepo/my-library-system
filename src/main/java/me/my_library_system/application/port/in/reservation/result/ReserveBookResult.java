package me.my_library_system.application.port.in.reservation.result;

import me.my_library_system.domain.reservation.Reservation;

import java.time.LocalDateTime;

public record ReserveBookResult(Long reservationId, int waitingOrder, LocalDateTime reservedAt) {

    public static ReserveBookResult from(Reservation reservation) {
        return new ReserveBookResult(reservation.getId(), 0, LocalDateTime.now());
    }
}
