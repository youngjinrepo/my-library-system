package me.my_library_system.domain.loan;

import java.util.Optional;

public interface LoanRepository{
    Optional<Loan> findByMemberIdAndBookId(Long memberId, Long bookItemId);

    void save(Loan loan);

    Optional<Loan> findById(Long loanId);

    int countByMemberIdAndStatus(Long id, LoanStatus loanStatus);
}
