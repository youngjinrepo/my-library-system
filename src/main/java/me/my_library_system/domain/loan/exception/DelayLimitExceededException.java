package me.my_library_system.domain.loan.exception;

import me.my_library_system.common.exception.ErrorCode;

public class DelayLimitExceededException extends LoanException {
    public DelayLimitExceededException(int maxRenewalCnt) {
        super(ErrorCode.LOAN_RENEWAL_LIMIT_EXCEEDED,
                String.format("촤대 밥납 연기 횟수 %s회를 초과했습니다.", maxRenewalCnt));
    }
}
