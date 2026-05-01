package me.my_library_system.domain.reservation.exception;

import me.my_library_system.common.exception.ErrorCode;

public class DuplicatedReservationException extends ReservationException {

    public DuplicatedReservationException() {
        super(ErrorCode.DUPLICATED_RESERVATION, "동일한 예약 내역이 존재합니다.");
    }
}
