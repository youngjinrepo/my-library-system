package me.my_library_system.application.service;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.Library;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.Member;
import me.my_library_system.repository.BookItemRepository;
import me.my_library_system.repository.LoanRepository;
import me.my_library_system.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoanService {

    private final MemberRepository memberRepository;

    private final BookItemRepository bookItemRepository;
    private final LoanRepository loanRepository;
    @Transactional
    public void loan(Long memberId, Long bookItemId){
        Member member = memberRepository.findById(memberId).orElseThrow();
        BookItem bookItem = bookItemRepository.findById(bookItemId).orElseThrow();

        bookItem.isAvailable();
        member.canBorrow();


    }
}
