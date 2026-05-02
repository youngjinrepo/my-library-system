package me.my_library_system.domain.loan;

import java.time.Clock;
import java.time.LocalDate;

public class LoanFixture {

    public static Loan createLoan(Clock clock, LoanPolicy policy, LoanCreateContext context) {
        return Loan.createLoan(10L, 101L, clock, policy, context);
    }


    public static Loan createLoan(Clock clock) {
        LoanPolicy loanPolicy = new LoanPolicy(5, 7, 3);
        LocalDate dueDate = LocalDate.now(clock).plusDays(loanPolicy.dueDays());
        LoanCreateContext loanCreateContext = new LoanCreateContext(0, dueDate);
        return createLoan(clock, loanPolicy, loanCreateContext);
    }
}
