package me.my_library_system.domain.book.exception;

import me.my_library_system.common.exception.ErrorCode;

public class BookRemovalNotAllowed extends BookException {
    public BookRemovalNotAllowed(String message) {
        super(ErrorCode.BOOK_REMOVAL_NOT_ALLOWED, message);
    }

}
