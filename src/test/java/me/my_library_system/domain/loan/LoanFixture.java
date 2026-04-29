package me.my_library_system.domain.loan;

import java.time.Clock;

public class LoanFixture {

    public static Loan createLoan(Clock clock) {
        return Loan.createLoan(1L, 1L ,3, clock);
    }

}
