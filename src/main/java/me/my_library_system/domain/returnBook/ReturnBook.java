package me.my_library_system.domain.returnBook;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.time.Clock;
import java.time.LocalDateTime;

@Entity
@Getter
public class ReturnBook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long loanId;
    private Long bookId;
    private Long memberId;
    private LocalDateTime returnDate;

    public static ReturnBook createReturnBook(Long loanId,  Long bookId, Long memberId, LocalDateTime returnDate) {
        ReturnBook returnBook = new ReturnBook();
        returnBook.loanId = loanId;
        returnBook.bookId = bookId;
        returnBook.memberId = memberId;
        returnBook.returnDate = returnDate;
        return returnBook;
    }
}
