package me.my_library_system.adapter.out.persistence.jpa;

import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.loan.LoanFixture;
import me.my_library_system.domain.loan.LoanStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class LoanJpaRepositoryTest {

    @Autowired private LoanJpaRepository loanJpaRepository;

    @Test
    void 이용자ID와_책ID로_대출정보를_조회한다() {

        loanJpaRepository.save(LoanFixture.createLoan());
        Optional<Loan> loan = loanJpaRepository.findByMemberIdAndBookId(1L, 1L);

        assertThat(loan).isPresent();
        assertThat(loan.get().getMemberId()).isEqualTo(1);
        assertThat(loan.get().getBookId()).isEqualTo(1L);
    }

    @Test
    void 이용자id로_대출건수_조회() {
        assertThat(loanJpaRepository.countByMemberIdAndStatus(1L, LoanStatus.LOAN)).isEqualTo(0);
    }

    @Test
    void 이용자id로_연체중인_도서_조회(){
        assertThat(loanJpaRepository.countByMemberIdAndStatusAndDueDateBefore(1L, LoanStatus.LOAN, LocalDateTime.now()));
    }

}