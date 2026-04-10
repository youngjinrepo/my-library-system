package me.my_library_system.domain.book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
        bookItem.setCode(generateCode(request.code(), request.bookNo()));
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

    public boolean isAvailable() {
        return this.status==BookItemStatus.SHELVING;
    }

    public void unavailable() {
        if (this.status==BookItemStatus.LOANED) {
            throw new IllegalStateException("대출중인 도서는 비활성화 할 수 없습니다.");
        }
        this.status = BookItemStatus.SHELVING;
    }

}