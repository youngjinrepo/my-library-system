package me.my_library_system.application.returnBook;

import lombok.RequiredArgsConstructor;
import me.my_library_system.common.exception.EntityNotFoundException;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.book.BookItemRepository;
import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.member.Member;
import me.my_library_system.domain.member.MemberRepository;
import me.my_library_system.domain.returnBook.ReturnBook;
import me.my_library_system.domain.returnBook.ReturnBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


@Service
@RequiredArgsConstructor
public class ReturnBookService {

    private final LoanRepository loanRepository;
    private final BookItemRepository bookItemRepository;
    private final ReturnBookRepository returnBookRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void returnBook(Long loanId, LocalDateTime returnDateTime) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(() -> new EntityNotFoundException("Loan", loanId));
        BookItem bookItem = bookItemRepository.findById(loan.getBookItemId()).orElseThrow(() -> new EntityNotFoundException("BookItem", loan.getBookItemId()));
        Member member = memberRepository.findById(loan.getMemberId()).orElseThrow(() -> new EntityNotFoundException("Member", loan.getMemberId()));

        loan.returnLoan();
        bookItem.returnBook();

        LocalDate dueDate = loan.getDueDate();
        LocalDate returnDate = returnDateTime.toLocalDate();

        if (dueDate.isBefore(returnDate)) {
            long overdue = ChronoUnit.DAYS.between(dueDate, returnDate);
            member.suspend(overdue);
        }

        ReturnBook returnBook = ReturnBook.createReturnBook(loan.getId(), loan.getBookItemId(), loan.getMemberId(), returnDateTime);

        returnBookRepository.save(returnBook);
        bookItemRepository.save(bookItem);
        loanRepository.save(loan);
        memberRepository.save(member);
    }
}
