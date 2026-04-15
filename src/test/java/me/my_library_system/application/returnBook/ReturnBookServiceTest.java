package me.my_library_system.application.returnBook;

import me.my_library_system.domain.book.BookFixture;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.book.BookItemRepository;
import me.my_library_system.domain.loan.LoanFixture;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.member.MemberRepository;
import me.my_library_system.domain.returnBook.ReturnBookRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
                .willReturn(Optional.of(LoanFixture.createLoan()));
        given(bookItemRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.createLoanBookItem()));

        returnBookService.returnBook(1L);
        then(returnBookRepository).should(times(1)).save(Mockito.any());
    }


}