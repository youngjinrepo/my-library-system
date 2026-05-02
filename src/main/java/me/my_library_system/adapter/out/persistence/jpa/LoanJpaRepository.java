package me.my_library_system.adapter.out.persistence.jpa;

import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.loan.LoanStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface LoanJpaRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByMemberIdAndBookItemId(Long memberId, Long bookItemId);

    int countByMemberIdAndStatus(Long id, LoanStatus status);

    int countByMemberIdAndStatusAndDueDateBefore(Long memberId, LoanStatus loanStatus, LocalDate date);
}
