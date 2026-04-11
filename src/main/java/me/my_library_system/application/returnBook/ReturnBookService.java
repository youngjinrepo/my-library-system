package me.my_library_system.application.returnBook;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.book.BookItemRepository;
import me.my_library_system.domain.loan.Loan;
import me.my_library_system.domain.loan.LoanRepository;
import me.my_library_system.domain.returnBook.ReturnBook;
import me.my_library_system.domain.returnBook.ReturnBookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ReturnBookService {

    private final LoanRepository loanRepository;
    private final BookItemRepository bookItemRepository;
    private final ReturnBookRepository returnBookRepository;

    @Transactional
    public void returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        BookItem bookItem = bookItemRepository.findById(loan.getBookId()).orElseThrow();

        loan.returnLoan();
        bookItem.returnBook();

        //이용자 연체시에 대출정지

        ReturnBook returnBook = ReturnBook.createReturnBook(loan.getId(), loan.getMemberId(), loan.getBookId());
        returnBookRepository.save(returnBook);
        bookItemRepository.save(bookItem);
        loanRepository.save(loan);
    }
}
