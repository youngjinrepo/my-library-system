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
    INVALID_RESERVATION_STATE("R004", "현재 상태에서 수행할 수 없는 작업입니다.", HttpStatus.BAD_REQUEST),


    // Loan
    OVERDUE_LOAN("L001", "연체중인 도서가 있습니다.", HttpStatus.CONFLICT),
    LOAN_LIMIT_EXCEEDED("L002", "최대 대출 건수에 도달했습니다.", HttpStatus.BAD_REQUEST),
    INVALID_BOOK_STATE("L003", "현재 상태에서 수행할 수 없는 작업입니다.", HttpStatus.BAD_REQUEST),
    LOAN_DELAY_LIMIT_EXCEEDED("L004", "최대 반납 연기 횟수에 도달했습니다.", HttpStatus.BAD_REQUEST)
    ;
    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;
}
