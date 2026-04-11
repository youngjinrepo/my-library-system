package me.my_library_system.domain.returnBook;

public class ReturnFixture {
    public static ReturnBook createReturnBook() {
        return ReturnBook.createReturnBook(1L,1L, 1L);
    }
}
