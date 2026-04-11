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
@Setter(AccessLevel.PACKAGE)
@Getter
public class ReturnBook {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long loneId;
    private Long bookId;
    private Long memberId;
    LocalDateTime returnDate;

    public static ReturnBook createReturnBook() {
        return new ReturnBook();
    }
}
