package me.my_library_system.domain.reservation.exception;

import me.my_library_system.common.exception.BusinessException;
import me.my_library_system.common.exception.ErrorCode;

public abstract class ReservationException extends BusinessException {
    protected ReservationException(ErrorCode errorCode) {
        super(errorCode);
    }

    protected ReservationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
