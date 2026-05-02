package me.my_library_system.domain.loan;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public interface LoanRepository{
    Optional<Loan> findByMemberIdAndBookItemId(Long memberId, Long bookItemId);

    void save(Loan loan);

    Optional<Loan> findById(Long loanId);

    int countByMemberIdAndStatus(Long id, LoanStatus loanStatus);

    int countOverdueLoans(Long memberId, LocalDate date);
}
