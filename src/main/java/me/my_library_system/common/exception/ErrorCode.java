package me.my_library_system.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Reservation
    DUPLICATED_RESERVATION("R001", "동일한 예약 내역이 존재합니다.", HttpStatus.CONFLICT),
    RESERVATION_LIMIT_EXCEEDED("R002", "예약 가능 건수를 초과했습니다.", HttpStatus.BAD_REQUEST),
    RESERVATION_COOLDOWN("R003", "예약 취소 후 쿨다운 기간 중입니다.", HttpStatus.BAD_REQUEST),


    // Loan
    OVERDUE_LOAN("L001", "연체중인 도서가 있습니다.", HttpStatus.CONFLICT)


    ;
    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;
}
