package me.my_library_system.common.exception;

public class DuplicatedException extends RuntimeException{
    public DuplicatedException(String message) {
        super(message);
    }
}
