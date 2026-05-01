package me.my_library_system.common.exception;

public abstract class BusinessException extends LibraryException {
    protected BusinessException(ErrorCode errorCode) {
        super(errorCode);
    }

    protected BusinessException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
