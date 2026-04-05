package me.my_library_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long bookId;
    private Long memberId;
    private LocalDateTime loanDate;
    private LocalDateTime returnDate;
    private LocalDateTime dueDate;
    private int renewalCnt;

    public static Loan createLoan(Long memberId, Long bookId, int renewalCnt, long dueDaysCnt) {
        Loan loan = new Loan();
        loan.setMemberId(memberId);
        loan.setBookId(bookId);
        loan.setLoanDate(LocalDateTime.now());
        loan.setDueDate(LocalDateTime.now().plusDays(dueDaysCnt));
        loan.setRenewalCnt(renewalCnt);
        return loan;
    }
}
