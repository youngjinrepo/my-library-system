package me.my_library_system.application.loan;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.library.Library;
import me.my_library_system.domain.loan.*;
import me.my_library_system.domain.library.Policy;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.member.Member;
import me.my_library_system.domain.book.BookItemRepository;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.member.MemberRepository;
import me.my_library_system.domain.reservation.ReservationRepository;
import me.my_library_system.domain.reservation.ReservationStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final MemberRepository memberRepository;
    private final LibraryRepository libraryRepository;
    private final BookItemRepository bookItemRepository;
    private final LoanRepository loanRepository;
    private final ReservationRepository reservationRepository;
    private final Clock clock;

    @Transactional
    public void loan(Long memberId, Long bookItemId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        BookItem bookItem = bookItemRepository.findById(bookItemId).orElseThrow();
        Library library = libraryRepository.getLibrary();

        member.canBorrow();
        bookItem.canLoan();

        LoanPolicy loanPolicy = library.getLoanPolicy();
        int currentLoanCnt = loanRepository.countByMemberIdAndStatus(member.getId(), LoanStatus.LOAN);
        LocalDate dueDate = library.calculateDueDate(LocalDate.now(clock), loanPolicy.dueDays());

        LoanCreateContext context = new LoanCreateContext(currentLoanCnt, dueDate);

        bookItem.loan();

        loanRepository.save(Loan.createLoan(member.getId(), bookItem.getId(), clock, loanPolicy, context));
        bookItemRepository.save(bookItem);
    }

    @Transactional
    public void renewalDueDate(Long memberId, Long bookItemId) {
        Loan loan = loanRepository.findByMemberIdAndBookItemId(memberId, bookItemId)
                .orElseThrow(() -> new IllegalArgumentException("조회되는 대출 내역이 없습니다."));
        Library library = libraryRepository.getLibrary();

        BookItem bookItem = bookItemRepository.findById(bookItemId).orElseThrow();
        Long bookInfoId = bookItem.getBookInfo().getId();

        int reservationCount = reservationRepository.countByBookInfoIdAndStatus(bookInfoId, ReservationStatus.ACTIVE);
        if (reservationCount > 0) {
            throw new IllegalStateException("예약중인 이용자가 있어 밥납연기 할 수 없습니다.");
        }

        LoanPolicy loanPolicy = library.getLoanPolicy();
        LocalDate dueDate = library.calculateDueDate(loan.getDueDate(), loanPolicy.dueDays());

        loan.delayDueDate(dueDate, loanPolicy.maxRenewalCnt());

        loanRepository.save(loan);
    }
}
