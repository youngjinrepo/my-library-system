package me.my_library_system.domain.loan;

import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface LoanRepository{
    Optional<Loan> findByMemberIdAndBookId(Long memberId, Long bookItemId);

    void save(Loan loan);
}
