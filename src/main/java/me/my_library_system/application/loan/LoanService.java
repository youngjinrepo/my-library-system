package me.my_library_system.application.loan;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.library.Policy;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.member.Member;
import me.my_library_system.domain.book.BookItemRepository;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final MemberRepository memberRepository;
    private final LibraryRepository libraryRepository;
    private final BookItemRepository bookItemRepository;
    private final LoanRepository loanRepository;

    @Transactional
    public void loan(Long memberId, Long bookItemId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        BookItem bookItem = bookItemRepository.findById(bookItemId).orElseThrow();
        Policy policy = libraryRepository.getLibrary().getPolicy();

        if ( bookItem.isAvailable() ) {
            throw new IllegalStateException("도서가 이용 불가 상태입니다.");
        }
        member.canBorrow();
        policy.validateLoanCount(member.getLoans().size());

        loanRepository.save(Loan.createLoan(member.getId(), bookItem.getId(), policy.returnRenewalCnt(), policy.dueDays()));
    }
}
