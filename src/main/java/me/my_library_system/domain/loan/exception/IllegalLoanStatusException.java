package me.my_library_system.domain.loan.exception;

import me.my_library_system.common.exception.ErrorCode;

public class IllegalLoanStatusException extends LoanException {
    public IllegalLoanStatusException(String current, String operation) {
        super(ErrorCode.INVALID_LOAN_STATE,
                String.format("현재 상태(%s)에서는 '%s' 작업을 수행할 수 없습니다.", current, operation));
    }
}
