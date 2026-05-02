package me.my_library_system.domain.reservation.exception;

import me.my_library_system.common.exception.ErrorCode;
import me.my_library_system.domain.reservation.ReservationStatus;

public class IllegalReservationStateException extends ReservationException {

    public IllegalReservationStateException(ReservationStatus current, String operation) {
        super(ErrorCode.INVALID_RESERVATION_STATE,
                String.format("현재 상태(%s)에서는 '%s' 작업을 수행할 수 없습니다.", current, operation));
    }
}
