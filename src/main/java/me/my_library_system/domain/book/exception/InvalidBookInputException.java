package me.my_library_system.domain.book.exception;

import me.my_library_system.common.exception.ErrorCode;

public class InvalidBookInputException extends BookException{
    public InvalidBookInputException(String message) {
        super(ErrorCode.INVALID_BOOK_STATE, message);
    }
}
