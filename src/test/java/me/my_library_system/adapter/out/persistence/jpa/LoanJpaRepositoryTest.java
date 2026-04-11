package me.my_library_system.adapter.out.persistence.jpa;

import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.loan.LoanFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

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
}