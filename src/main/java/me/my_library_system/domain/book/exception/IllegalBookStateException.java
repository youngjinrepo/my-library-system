package me.my_library_system.domain.book.exception;

import me.my_library_system.common.exception.ErrorCode;

public class IllegalBookStateException extends BookException {
    public IllegalBookStateException(String status, String operation) {
        super(ErrorCode.INVALID_LOAN_STATE, String.format("현재 상태(%s)에서는 '%s' 작업을 수행할 수 없습니다.", status, operation));
    }
}
