package me.my_library_system.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import me.my_library_system.domain.enums.BookItemStatus;

@Entity
@Getter
@ToString
@Setter(AccessLevel.PROTECTED)
public class BookItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private String callNo;
    private BookItemStatus status;
    private String location;


    public static BookItem createBookItem(BookItemRegisterRequest request) {
        BookItem bookItem = new BookItem();
        bookItem.setCode(generateCode(Library.getCode(), request.bookNo()));
        bookItem.setCallNo(generateCallNo(request.classify(), request.author(), request.volume(), request.bookCnt()));
        bookItem.setStatus(BookItemStatus.CATALOGING);
        return bookItem;
    }

    private static String generateCallNo(String classify, String author, String volume, int bookCnt) {
        return String.format("%s-%s-%s-%d", classify, author.charAt(0), volume, bookCnt);
    }

    private static String generateCode(String code, int sequenceNo) {
        return String.format("%s%06d",code, sequenceNo);
    }
}