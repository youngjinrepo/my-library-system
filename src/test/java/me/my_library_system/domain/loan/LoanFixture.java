package me.my_library_system.domain.loan;

import me.my_library_system.domain.library.Policy;

public class LoanFixture {

    public static Loan createLoan() {
        return Loan.createLoan(1L, 1L ,3,10);
    }

}
