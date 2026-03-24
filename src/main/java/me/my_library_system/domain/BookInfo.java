package me.my_library_system.domain;

public class BookInfo {
    long id;
    String title;
    String author;
    String publisher;
    String ISBN;
    String marc;
    String classify;
    boolean isAdult;
    BookInfoStatus status;

    public BookInfo(String title, String author, String publisher, String ISBN, boolean isAdult) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.ISBN = ISBN;
        this.isAdult = isAdult;
        status=BookInfoStatus.DRAFT;
    }

    public static BookInfo acquireBook(String title, String author, String publisher, String ISBN, boolean isAdult){
        return new BookInfo(title, author, publisher, ISBN, isAdult);
    }
}
