package me.my_library_system.domain.library;

import jakarta.persistence.Embeddable;

@Embeddable
public record Policy(int maxLoanCnt, int returnRenewalCnt, int dueDays) {

    public void validateLoanCount(int currentLoanCnt) {
        if (maxLoanCnt <= currentLoanCnt) {
            throw new IllegalStateException("최대 대출 건수 (" + maxLoanCnt + ")");
        }
    }
}
