package me.my_library_system.domain.returnBook;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter(AccessLevel.PROTECTED)
@Getter
public class ReturnBook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long loanId;
    private Long bookId;
    private Long memberId;
    private LocalDateTime returnDate;

    public static ReturnBook createReturnBook(Long loanId,  Long bookId, Long memberId) {
        ReturnBook returnBook = new ReturnBook();
        returnBook.setLoanId(loanId);
        returnBook.setBookId(bookId);
        returnBook.setMemberId(memberId);
        returnBook.setReturnDate(LocalDateTime.now());
        return returnBook;
    }
}
