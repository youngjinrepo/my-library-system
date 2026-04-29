package me.my_library_system.domain.loan;

import me.my_library_system.TestClocks;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class LoanTest {


    @Test
    void loanTest() {
        Clock fixedClock = Clock.fixed(
                LocalDateTime.of(2026, 5, 15, 13, 0).atZone(TestClocks.zoneId).toInstant(),
                TestClocks.zoneId
        );

        Loan loan = Loan.createLoan(1L, 2L, 14, fixedClock);

        assertThat(loan.getLoanDate()).isEqualTo(LocalDateTime.of(2026, 5, 15, 13, 0));
        assertThat(loan.getDueDate()).isEqualTo(LocalDateTime.of(2026, 5, 29, 13, 0));
        assertThat(loan.getStatus()).isEqualTo(LoanStatus.LOAN);
    }
}