package me.my_library_system.adapter.in.web.reservation.response;

import me.my_library_system.application.port.in.reservation.result.ReserveBookResult;

import java.time.LocalDateTime;

public record ReserveBookResponse(Long reservationId, int waitingOrder, LocalDateTime reservedAt) {

    public static ReserveBookResponse from(ReserveBookResult reserveBookResult) {
        return new ReserveBookResponse(
                reserveBookResult.reservationId(),
                reserveBookResult.waitingOrder(),
                reserveBookResult.reservedAt());
    }
}
