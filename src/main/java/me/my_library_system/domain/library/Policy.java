package me.my_library_system.domain.library;

import jakarta.persistence.Embeddable;

@Embeddable
public record Policy(int maxLoanCnt, int returnRenewalCnt, int dueDays, int returnDelayDays) {



}
