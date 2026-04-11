package me.my_library_system.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.out.persistence.jpa.LoanJpaRepository;
import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.loan.LoanStatus;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class LoanPersistenceAdapter implements LoanRepository {

    private final LoanJpaRepository loanJpaRepository;

    @Override
    public Optional<Loan> findByMemberIdAndBookId(Long memberId, Long bookItemId) {
        return loanJpaRepository.findByMemberIdAndBookId(memberId, bookItemId);
    }

    @Override
    public void save(Loan loan) {
        loanJpaRepository.save(loan);
    }

    @Override
    public Optional<Loan> findById(Long loanId) {
        return loanJpaRepository.findById(loanId);
    }

    @Override
    public int countByMemberIdAndStatus(Long id, LoanStatus status) {
        return loanJpaRepository.countByMemberIdAndStatus(id, status);
    }
}
