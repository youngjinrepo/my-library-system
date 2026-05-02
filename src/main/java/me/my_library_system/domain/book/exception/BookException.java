package me.my_library_system.domain.book.exception;

import me.my_library_system.common.exception.BusinessException;
import me.my_library_system.common.exception.ErrorCode;

public class BookException extends BusinessException {
    protected BookException(ErrorCode errorCode) {
        super(errorCode);
    }

    protected BookException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
