package me.my_library_system.domain.loan.exception;

import me.my_library_system.common.exception.ErrorCode;

public class LoanLimitExceededException extends LoanException {
    public LoanLimitExceededException(int maxLoanCnt) {
        super(ErrorCode.LOAN_LIMIT_EXCEEDED,
                String.format("최대 대출 가능한 건수는 %s건 입니다.", maxLoanCnt));
    }
}
