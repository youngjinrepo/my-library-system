package me.my_library_system.common.exception;

import lombok.Getter;

@Getter
public abstract class LibraryException extends RuntimeException {

    private final ErrorCode errorCode;

    protected LibraryException(ErrorCode errorCode) {
        super(errorCode.getDefaultMessage());
        this.errorCode = errorCode;
    }

    protected LibraryException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

}
