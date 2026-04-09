package me.my_library_system.application.loan;

import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.member.Member;
import me.my_library_system.domain.book.BookInfo;
import me.my_library_system.fixture.LoanFixture;
import me.my_library_system.domain.book.BookItemRepository;
import me.my_library_system.domain.loan.LoanRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class LoanServiceTest {
    @Autowired
    LoanService loanService;
    @Autowired
    LoanFixture loanFixture;
    @Autowired
    BookItemRepository bookItemRepository;
    @Autowired
    LoanRepository loanRepository;

    @Test
    void loanTest() {
        Member member = loanFixture.getLoanableMember();
        BookInfo bookInfo = loanFixture.createCatalogedBook();
        loanService.loan(member.getId(), bookInfo.getId());
        List<Loan> all = loanRepository.findAll();
        assertThat(all.size()).isEqualTo(1);
    }
}
