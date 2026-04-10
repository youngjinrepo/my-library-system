package me.my_library_system.application.loan;

import me.my_library_system.domain.book.*;
import me.my_library_system.domain.library.Library;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.library.Policy;
import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.member.MemberFixture;
import me.my_library_system.domain.member.MemberRepository;
import me.my_library_system.domain.loan.LoanRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

public class LoanServiceTest {
    MemberRepository memberRepository = mock(MemberRepository.class);
    LibraryRepository libraryRepository = mock(LibraryRepository.class);
    BookItemRepository bookItemRepository = mock(BookItemRepository.class);
    LoanRepository loanRepository = mock(LoanRepository.class);
    LoanService loanService = new LoanService(memberRepository, libraryRepository, bookItemRepository, loanRepository);

    @Test
    void 대출_성공() {
        BookItem firstBook = BookFixture.createShelvingBookInfo().getBookItems().getFirst();

        given(memberRepository.findById(1L))
                .willReturn(Optional.of(MemberFixture.createRegularMember()));
        given(bookItemRepository.findById(1L))
                .willReturn(Optional.of(firstBook));
        given(libraryRepository.getLibrary())
                .willReturn(new Library(1L, "12", "13", new Policy(3, 1, 10)));


        loanService.loan(1L , 1L);

        then(loanRepository).should(times(1)).save(any(Loan.class));
    }

    @Test
    void 준회원은_대출_실패한다() {
        BookItem firstBook = BookFixture.createShelvingBookInfo().getBookItems().getFirst();

        given(memberRepository.findById(1L))
                .willReturn(Optional.of(MemberFixture.createAssociateMember()));
        given(bookItemRepository.findById(1L))
                .willReturn(Optional.of(firstBook));
        given(libraryRepository.getLibrary())
                .willReturn(new Library(1L, "12", "13", new Policy(3, 1, 10)));


        assertThatThrownBy(() -> loanService.loan(1L , 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("대출 불가한 이용자 입니다.");
    }

    @Test
    void 대출불가_도서는_대출_실패한다() {
        BookItem firstBook = BookFixture.createCatalogingBookInfo().getBookItems().getFirst();

        given(memberRepository.findById(1L))
                .willReturn(Optional.of(MemberFixture.createRegularMember()));
        given(bookItemRepository.findById(1L))
                .willReturn(Optional.of(firstBook));
        given(libraryRepository.getLibrary())
                .willReturn(new Library(1L, "12", "13", new Policy(3, 1, 10)));


        assertThatThrownBy(() -> loanService.loan(1L , 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("도서가 이용 불가 상태입니다.");
    }

    @Test
    void 최대대출건수를_넘으면_대출_실패한다() {
        BookItem firstBook = BookFixture.createShelvingBookInfo().getBookItems().getFirst();

        given(memberRepository.findById(1L))
                .willReturn(Optional.of(MemberFixture.createRegularMember()));
        given(bookItemRepository.findById(1L))
                .willReturn(Optional.of(firstBook));
        given(libraryRepository.getLibrary())
                .willReturn(new Library(1L, "12", "13", new Policy(0, 1, 10)));


        assertThatThrownBy(() -> loanService.loan(1L , 1L))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("최대 대출 건수 (");
    }
}
