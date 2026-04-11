package me.my_library_system.adapter.out.persistence.jpa;

import me.my_library_system.domain.loan.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanJpaRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByMemberIdAndBookId(Long memberId, Long bookItemId);
}
