package me.my_library_system.domain.reservation.exception;

import me.my_library_system.common.exception.ErrorCode;

public class ReservationCooldownException extends ReservationException {
    public ReservationCooldownException(int cancellationCooldown) {
        super(ErrorCode.RESERVATION_COOLDOWN, "동일한 도서는 예약 취소 후 "+cancellationCooldown+"간 예약이 불가능 합니다.");
    }
}
