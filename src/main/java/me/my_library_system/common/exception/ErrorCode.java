package me.my_library_system.common.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.HashSet;
import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // Reservation (R)
    DUPLICATED_RESERVATION("R001", "동일한 예약 내역이 존재합니다.", HttpStatus.CONFLICT),
    RESERVATION_LIMIT_EXCEEDED("R002", "예약 가능 건수를 초과했습니다.", HttpStatus.CONFLICT),
    RESERVATION_COOLDOWN("R003", "예약 취소 후 쿨다운 기간 중입니다.", HttpStatus.CONFLICT),
    INVALID_RESERVATION_STATE("R004", "현재 상태에서 수행할 수 없는 작업입니다.", HttpStatus.BAD_REQUEST),

    // Loan (L)
    OVERDUE_LOAN("L001", "연체중인 도서가 있습니다.", HttpStatus.CONFLICT),
    LOAN_LIMIT_EXCEEDED("L002", "최대 대출 건수에 도달했습니다.", HttpStatus.CONFLICT),
    INVALID_LOAN_STATE("L003", "현재 상태에서 수행할 수 없는 작업입니다.", HttpStatus.CONFLICT),
    LOAN_RENEWAL_LIMIT_EXCEEDED("L004", "최대 반납 연기 횟수에 도달했습니다.", HttpStatus.CONFLICT),
    RENEWAL_NOT_ALLOWED("L005", "예약 이용자가 존재해 갱신할 수 없습니다.", HttpStatus.CONFLICT),

    //Member (M)
    MEMBER_NOT_BORROWABLE("M001", "대출 불가한 이용자입니다.", HttpStatus.CONFLICT),
    MEMBER_SUSPENDED("M002", "정지 상태의 이용자입니다.", HttpStatus.CONFLICT),
    INVALID_MEMBER_STATE("M003", "현재 단계에선 수행할 수 없는 작업입니다.", HttpStatus.BAD_REQUEST),
    INVALID_MEMBER_INPUT("M004", "회원 정보 입력값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),

    //Book (B)
    INVALID_BOOK_STATE("B001", "현재 상태에서 수행할 수 없는 작업입니다.", HttpStatus.BAD_REQUEST),
    BOOK_REMOVAL_NOT_ALLOWED("B002", "대출중인 도서가 있어 제거할 수 없습니다.", HttpStatus.CONFLICT),
    INVALID_BOOK_INPUT("B003", "도서 입력값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),

    // 공통 (E)
    ENTITY_NOT_FOUND("E001", "리소스를 찾을 수 없습니다.", HttpStatus.NOT_FOUND),
    INVALID_INPUT("E002", "입력값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("E500", "서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final String code;
    private final String defaultMessage;
    private final HttpStatus status;

    static {
        Set<String> codeSet = new HashSet<>();
        for (ErrorCode errorCode : values()) {
            if (!codeSet.add(errorCode.code)) {
                throw new IllegalStateException("Duplicate code " + errorCode.code);
            }
        }
    }
}
