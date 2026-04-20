package me.my_library_system.domain.book;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static me.my_library_system.domain.book.BookItem.*;

@Entity
@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BookInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String volume;
    private String author;
    private String publisher;
    private String ISBN;
    private String marc;
    private String classify;
    boolean isAdult;
    @Enumerated(EnumType.STRING)
    private BookInfoStatus status;
    @OneToMany(mappedBy = "bookInfo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookItem> bookItems = new ArrayList<>();

    private BookInfo(String title, String volume, String author, String publisher, String ISBN, boolean isAdult) {
        this.title = title;
        this.volume = volume;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.isAdult = isAdult;
        status=BookInfoStatus.DRAFT;
    }

    public static BookInfo acquireBook(String title, String volume, String author, String publisher, String ISBN, boolean isAdult){
        return new BookInfo(title, volume, author, publisher, ISBN, isAdult);
    }

    public void cataloging(String classify, int bookCnt, int startSequence,String code) {
        if ( this.id==null ) {
            throw new RuntimeException("Book ID is null");
        }
        if ( this.status != BookInfoStatus.DRAFT) {
            throw new IllegalStateException("DRAFT 상태일 때만 편목이 가능합니다.");
        }
        if ( bookCnt <= 0 ) {
            throw new IllegalArgumentException("도서의 갯수를 올바르게 입력해주세요");
        }

        for (int i = 0; i < bookCnt; i++) {
            this.bookItems.add(createBookItem(new BookItemRegisterRequest(this, this.title, this.volume, this.author, classify, startSequence+i, i+1, code)));
        }

        this.classify = classify;
        this.status = BookInfoStatus.CATALOGING;
    }

    public void shelve(String location) {
        if ( this.status != BookInfoStatus.CATALOGING) {
            throw new IllegalStateException("CATALOGING 상태일 때만 배가가 가능합니다.");
        }
        if (location.isEmpty()) {
            throw new IllegalArgumentException("배가 위치를 입력 해야합니다.");
        }
        this.status = BookInfoStatus.COMPLETED;
        this.bookItems.forEach(bookItem -> {
            bookItem.setStatus(BookItemStatus.SHELVING);
            bookItem.setLocation(location);
        });
    }

    public void validateRemovable(){
        this.bookItems.forEach(bookItem -> {
            if (bookItem.getStatus().equals(BookItemStatus.LOANED)) {
                throw new IllegalStateException("대출중인 도서가 있을 경우 서지 정보를 제거 할 수 없습니다.");
            }
        });

        //예약 도입되면 예약 내역에서 제거
    }
}
