package me.my_library_system.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
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
    private BookInfoStatus status;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookItem> bookItems = new ArrayList<>();

    public BookInfo(String title, String volume, String author, String publisher, String ISBN, boolean isAdult) {
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

    public void cataloging(String classify, String location, int bookCnt) {
        if ( this.id==null ) {
            throw new RuntimeException("Book ID is null");
        }
        if ( this.status != BookInfoStatus.DRAFT) {
            throw new IllegalStateException("DRAFT 상태일 때만 편목이 가능합니다.");
        }

        for (int i = 0; i < bookCnt; i++) {
            bookItems.add(BookItem.createBookItem(new BookItemRegisterRequest(this.title, this.volume, this.author, location, classify, i)));
        }

        this.classify = classify;
        this.status = BookInfoStatus.CATALOGING;
    }
}
