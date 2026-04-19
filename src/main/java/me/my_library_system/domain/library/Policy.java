package me.my_library_system.domain.library;

import jakarta.persistence.Embeddable;

@Embeddable
public record Policy(int maxLoanCnt, int returnRenewalCnt, int dueDays, int returnDelayDays, int maxReservationCnt, int cancellationCooldown) {

    public void validateLoanCount(int currentLoanCnt) {
        if (maxLoanCnt <= currentLoanCnt) {
            throw new IllegalStateException("최대 대출 건수 (" + maxLoanCnt + ")");
        }
    }

    public void validateMaxRenewalCount(int renewalCnt) {
        if (renewalCnt >= returnRenewalCnt) {
            throw new IllegalStateException("최대 반납연기 횟수를 초과 하였습니다.");
        }
    }

    public void validateMaxReservationCnt(int reservationCnt) {
        if (reservationCnt >= maxReservationCnt) {
            throw new IllegalStateException("최대 예약 가능 건수 (" + maxReservationCnt + ")");
        }
    }
}
