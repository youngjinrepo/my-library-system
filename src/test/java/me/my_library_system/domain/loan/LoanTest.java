package me.my_library_system.domain.loan;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LoanTest {


    @Test
    void loanTest() {
        int days = 14;
        Loan loan = Loan.createLoan(1L, 2L, 3, days);

        assertThat(loan).isNotNull();
        assertThat(loan.getLoanDate().plusDays(days)).isEqualTo(loan.getDueDate());
    }
}