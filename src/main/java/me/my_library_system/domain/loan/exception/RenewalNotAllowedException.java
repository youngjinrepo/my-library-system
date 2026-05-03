package me.my_library_system.domain.loan.exception;

import me.my_library_system.common.exception.ErrorCode;

public class RenewalNotAllowedException extends LoanException {
    public RenewalNotAllowedException(String message) {
        super(ErrorCode.RENEWAL_NOT_ALLOWED, message);
    }
}
