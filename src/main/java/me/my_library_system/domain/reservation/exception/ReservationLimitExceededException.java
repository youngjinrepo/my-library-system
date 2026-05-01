package me.my_library_system.domain.reservation.exception;

import me.my_library_system.common.exception.ErrorCode;

public class ReservationLimitExceededException extends ReservationException{

    public ReservationLimitExceededException(int maxReservationCnt) {
        super(ErrorCode.RESERVATION_LIMIT_EXCEEDED, "최대 예약 건수를 초과했습니다.("+maxReservationCnt+")");
    }
}
