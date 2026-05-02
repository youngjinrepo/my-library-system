package me.my_library_system.domain.loan;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.my_library_system.domain.loan.exception.DelayLimitExceededException;
import me.my_library_system.domain.loan.exception.IllegalLoanStatusException;
import me.my_library_system.domain.loan.exception.LoanLimitExceededException;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookItemId;
    private Long memberId;
    private LocalDateTime loanDate;
    private LocalDate dueDate;
    private int renewalCnt;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    public static Loan createLoan(Long memberId, Long bookItemId, Clock clock, LoanPolicy policy, LoanCreateContext context) {
        validate(policy, context);

        Loan loan = new Loan();
        loan.memberId = memberId;
        loan.bookItemId = bookItemId;
        loan.loanDate = LocalDateTime.now(clock);
        loan.dueDate = context.dueDate();
        loan.renewalCnt = 0;
        loan.status = LoanStatus.LOAN;
        return loan;
    }

    private static void validate(LoanPolicy policy, LoanCreateContext context) {
        if (policy.maxLoanCnt() <= context.currentLoanCnt()) {
            throw new LoanLimitExceededException(policy.maxLoanCnt());
        }
    }

    public void delayDueDate(LocalDate dueDate, int maxRenewalCnt) {
        if ( this.status != LoanStatus.LOAN) {
            throw new IllegalLoanStatusException(this.status.name(), "반난연기");
        }
        if (this.renewalCnt >= maxRenewalCnt) {
            throw new DelayLimitExceededException(maxRenewalCnt);
        }

        this.dueDate = dueDate;
        this.renewalCnt++;
    }

    public void returnLoan() {
        this.status = LoanStatus.RETURNED;
    }
}
