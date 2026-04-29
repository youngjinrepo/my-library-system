package me.my_library_system.domain.loan;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Clock;
import java.time.LocalDateTime;

@Entity
@Getter
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private Long memberId;
    private LocalDateTime loanDate;
    private LocalDateTime dueDate;
    private int renewalCnt;
    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    public static Loan createLoan(Long memberId, Long bookId, long dueDaysCnt, Clock clock) {
        LocalDateTime now = LocalDateTime.now(clock);

        Loan loan = new Loan();
        loan.memberId =memberId;
        loan.bookId = bookId;
        loan.loanDate = now;
        loan.dueDate = now.plusDays(dueDaysCnt);
        loan.renewalCnt = 0;
        loan.status = LoanStatus.LOAN;
        return loan;
    }

    public void increaseRenewalCnt() {
        this.renewalCnt++;
    }

    public void delayDueDate(int days) {



        this.dueDate = this.dueDate.plusDays(days);
    }

    public void returnLoan() {
        this.status = LoanStatus.RETURNED;
    }
}
