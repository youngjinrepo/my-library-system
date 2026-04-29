package me.my_library_system.domain.book;

public class BookFixture {

    public static BookInfo createBookInfo() {
        BookInfo bookInfo = BookInfo.acquireBook("해리 포터와 마법사의 돌 1", "1권", "J.K. 롤링 저자", "문학수첩", "9791193790403", false);
        bookInfo.assignId(1L);
        return bookInfo;
    }

    public static BookInfo createCatalogingBookInfo() {
        BookInfo harryPotter = createBookInfo();
        harryPotter.cataloging("800", 3, 1, "CD");
        return harryPotter;
    }

    public static BookInfo createShelvingBookInfo() {
        BookInfo harryPotter = createCatalogingBookInfo();
        harryPotter.shelve("1층");
        return harryPotter;
    }

    public static BookItem createLoanBookItem() {
        BookInfo shelvingBookInfo = createShelvingBookInfo();
        BookItem bookItem = shelvingBookInfo.getBookItems().getFirst();
        bookItem.loan();
        return bookItem;
    }

    public static BookInfo emptyBookInfo() {
        return new BookInfo();
    }
}
