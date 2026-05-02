package me.my_library_system.domain.loan;

import me.my_library_system.TestClocks;
import me.my_library_system.domain.loan.exception.IllegalLoanStatusException;
import me.my_library_system.domain.loan.exception.LoanLimitExceededException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static me.my_library_system.domain.loan.LoanFixture.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LoanTest {
    private final LoanPolicy loanPolicy = new LoanPolicy(5, 7, 3);
    private final Clock fixedClock = TestClocks.at("2026-05-15T13:00");

    @Nested
    class CreateLoan {
        @Test
        void 대출_정상_생성() {
            LocalDate dueDate = LocalDate.of(2026, 5, 22);
            LoanCreateContext loanCreateContext = new LoanCreateContext(0, dueDate);

            Loan loan = Loan.createLoan(10L, 101L, fixedClock, loanPolicy, loanCreateContext);

            assertThat(loan.getMemberId()).isEqualTo(10L);
            assertThat(loan.getBookItemId()).isEqualTo(101L);
            assertThat(loan.getStatus()).isEqualTo(LoanStatus.LOAN);
            assertThat(loan.getLoanDate()).isEqualTo(LocalDateTime.now(fixedClock));
            assertThat(loan.getDueDate()).isEqualTo(dueDate);
        }
        @Test
        void 대출_한도_직적이면_대출_가능() {
            LoanCreateContext loanCreateContext = new LoanCreateContext(loanPolicy.maxLoanCnt()-1, LocalDate.now(fixedClock));
            Loan loan = Loan.createLoan(10L, 101L, fixedClock, loanPolicy, loanCreateContext);
            assertThat(loan).isNotNull();
        }

        @Test
        void 대출_한도_도달하면_LoanLimitExceededException() {
            LoanCreateContext loanCreateContext = new LoanCreateContext(loanPolicy.maxLoanCnt(), LocalDate.now(fixedClock));

            assertThatThrownBy(()->Loan.createLoan(10L, 101L, fixedClock, loanPolicy, loanCreateContext))
                    .isInstanceOf(LoanLimitExceededException.class);
        }

    }

    @Nested
    class delayLoan {
        @Test
        void 대출_연기_성공() {
            LocalDate dueDate = LocalDate.of(2026, 5, 22);
            LoanCreateContext loanCreateContext = new LoanCreateContext(0, dueDate);
            Loan loan = createLoan(fixedClock, loanPolicy, loanCreateContext);

            loan.delayDueDate(loan.getDueDate().plusDays(loanPolicy.dueDays()), loanPolicy.maxRenewalCnt());

            assertThat(loan.getDueDate()).isEqualTo(LocalDate.of(2026, 5, 29));
        }

        @Test
        void 대출_상태가_아닐때_연기하면_IllegalLoanStatusException() {
            LocalDate dueDate = LocalDate.of(2026, 5, 22);
            LoanCreateContext loanCreateContext = new LoanCreateContext(0, dueDate);
            Loan loan = createLoan(fixedClock, loanPolicy, loanCreateContext);
            loan.returnLoan();

            assertThatThrownBy(()->loan.delayDueDate(loan.getDueDate(), loanPolicy.maxRenewalCnt()))
                    .isInstanceOf(IllegalLoanStatusException.class);
        }

    }
}