package me.my_library_system.application.returnBook;

import me.my_library_system.domain.book.BookFixture;
import me.my_library_system.domain.book.BookItemRepository;
import me.my_library_system.domain.loan.LoanFixture;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.member.MemberFixture;
import me.my_library_system.domain.member.MemberRepository;
import me.my_library_system.domain.returnBook.ReturnBookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

import static org.mockito.BDDMockito.*;

class ReturnBookServiceTest {
    LoanRepository loanRepository = Mockito.mock(LoanRepository.class);
    BookItemRepository bookItemRepository = Mockito.mock(BookItemRepository.class);
    ReturnBookRepository returnBookRepository = Mockito.mock(ReturnBookRepository.class);
    MemberRepository memberRepository = Mockito.mock(MemberRepository.class);
    ReturnBookService returnBookService = new ReturnBookService(loanRepository, bookItemRepository, returnBookRepository, memberRepository);

    @Test
    void 반납_테스트() {
        given(loanRepository.findById(1L))
                .willReturn(Optional.of(LoanFixture.createLoan(Clock.systemDefaultZone())));
        given(bookItemRepository.findById(101L))
                .willReturn(Optional.of(BookFixture.createLoanBookItem()));
        given(memberRepository.findById(10L))
                .willReturn(Optional.of(MemberFixture.createRegularMember()));

        returnBookService.returnBook(1L, LocalDateTime.now());

        then(returnBookRepository).should(times(1)).save(Mockito.any());
    }


    @Test
    void 반납_예정일_계산_로직_검증(){
        LocalDate dueDate = LocalDateTime.now().minusDays(1).toLocalDate();
        LocalDate today = LocalDateTime.now().toLocalDate();
        long overdue = 0L;
        if (dueDate.isBefore(today)) {
            overdue = ChronoUnit.DAYS.between(dueDate, today);
        }
        Assertions.assertThat(overdue).isEqualTo(1L);

        dueDate = LocalDateTime.now().plusDays(1).toLocalDate();
        today = LocalDateTime.now().toLocalDate();

        overdue = ChronoUnit.DAYS.between(dueDate, today);

        Assertions.assertThat(overdue).isEqualTo(-1L);
    }
}