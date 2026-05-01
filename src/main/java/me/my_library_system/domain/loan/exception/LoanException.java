package me.my_library_system.domain.loan.exception;

import me.my_library_system.common.exception.BusinessException;
import me.my_library_system.common.exception.ErrorCode;

public class LoanException extends BusinessException {
    protected LoanException(ErrorCode errorCode) {
        super(errorCode);
    }

    protected LoanException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
