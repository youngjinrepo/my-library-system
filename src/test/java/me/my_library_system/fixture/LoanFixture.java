package me.my_library_system.fixture;

import me.my_library_system.application.book.BookCatalogingService;
import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.member.Member;
import me.my_library_system.domain.book.BookInfo;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.book.BookInfoRepository;
import me.my_library_system.domain.book.BookItemRepository;
import me.my_library_system.domain.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class LoanFixture {
    @Autowired private BookInfoRepository  bookInfoRepository;
    @Autowired private BookItemRepository bookItemRepository;
    @Autowired private MemberRepository memberRepository;
    @Autowired private BookCatalogingService bookCatalogingService;


    public Loan createLoan() {
        BookInfo harryPotter = createCatalogedBook();
        //harryPotter = bookInfoRepository.findById(harryPotter.getId()).orElse(null);
        BookItem bookItem = Objects.requireNonNull(harryPotter).getBookItems().getFirst();

        Member member = createMember();

        return Loan.createLoan(member.getId(), bookItem.getId(), 3,10);
    }

    public BookInfo createBookInfo() {
        BookInfo harryPotter = BookInfo.acquireBook("해리 포터와 마법사의 돌 1", "1권", "J.K. 롤링 저자", "문학수첩", "9791193790403", false);
        return bookInfoRepository.save(harryPotter);
    }

    public BookInfo createCatalogedBook() {
        BookInfo harryPotter = createBookInfo();
        bookCatalogingService.processCataloging(harryPotter.getId(), "800", 3);
        return harryPotter;
    }

    public Member createMember() {
        Member member = Member.createMember("김대기", "kim.wait@name.com", "남", 10, "ox빌라 302호");
        return memberRepository.save(member);
    }

    public Member getLoanableMember() {
        Member member = createMember();
        Member.promoteMember(member, "ci");
        return memberRepository.save(member);
    }
}
