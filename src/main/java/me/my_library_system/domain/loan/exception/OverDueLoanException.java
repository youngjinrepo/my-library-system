package me.my_library_system.domain.loan.exception;

import me.my_library_system.common.exception.ErrorCode;

public class OverDueLoanException extends LoanException {

    public OverDueLoanException(String message) {
        super(ErrorCode.OVERDUE_LOAN, message);
    }
}
