package me.my_library_system.domain.book;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import me.my_library_system.domain.book.exception.IllegalBookStateException;

@Entity
@Getter
@ToString
public class BookItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private BookInfo bookInfo;
    private String code;
    private String callNo;
    @Enumerated(EnumType.STRING)
    private BookItemStatus status;
    private String location;

    public static BookItem createBookItem(BookItemRegisterRequest request) {
        BookItem bookItem = new BookItem();
        bookItem.bookInfo = request.bookInfo();
        bookItem.code = generateCode(request.code(), request.bookNo());
        bookItem.callNo = generateCallNo(request.classify(), request.author(), request.volume(), request.bookCnt());
        bookItem.status = BookItemStatus.CATALOGING;
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
            throw new IllegalBookStateException(BookItemStatus.LOANED.name(), "비활성");
        }
        this.status = BookItemStatus.UNAVAILABLE;
    }

    public void returnBook() {
        this.status=BookItemStatus.SHELVING;
    }

    public void loan() {
        this.status=BookItemStatus.LOANED;
    }

    public void shelving(String location) {
        this.status =  BookItemStatus.SHELVING;
        this.location = location;
    }

    public void canLoan() {
        if (!this.isAvailable()) {
            throw new IllegalBookStateException(this.status.name(), "대출");
        }
    }
}