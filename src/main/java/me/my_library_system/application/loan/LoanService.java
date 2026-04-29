package me.my_library_system.application.loan;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.library.Policy;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.loan.LoanStatus;
import me.my_library_system.domain.member.Member;
import me.my_library_system.domain.book.BookItemRepository;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final MemberRepository memberRepository;
    private final LibraryRepository libraryRepository;
    private final BookItemRepository bookItemRepository;
    private final LoanRepository loanRepository;
    private final Clock clock;

    @Transactional
    public void loan(Long memberId, Long bookItemId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        BookItem bookItem = bookItemRepository.findById(bookItemId).orElseThrow();
        Policy policy = libraryRepository.getLibrary().getPolicy();

        if ( !bookItem.isAvailable() ) {
            throw new IllegalStateException("도서가 이용 불가 상태입니다.");
        }
        member.canBorrow();
        int loanCnt = loanRepository.countByMemberIdAndStatus(member.getId(), LoanStatus.LOAN);
        policy.validateLoanCount(loanCnt);

        bookItem.loan();
        loanRepository.save(Loan.createLoan(member.getId(), bookItem.getId(), policy.dueDays(), clock));
    }

    @Transactional
    public void renewalDueDate(Long memberId, Long bookItemId) {
        Loan loan = loanRepository.findByMemberIdAndBookId(memberId, bookItemId)
                .orElseThrow(() -> new IllegalArgumentException("조회되는 대출 내역이 없습니다."));
        Policy policy = libraryRepository.getLibrary().getPolicy();
        //예약 정보가 있다면 막는다.
        policy.validateMaxRenewalCount(loan.getRenewalCnt());
        loan.increaseRenewalCnt();
        loan.delayDueDate(policy.returnDelayDays());
    }
}
