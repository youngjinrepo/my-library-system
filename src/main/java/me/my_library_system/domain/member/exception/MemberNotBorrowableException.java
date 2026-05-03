package me.my_library_system.domain.member.exception;

import me.my_library_system.common.exception.ErrorCode;

public class MemberNotBorrowableException extends MemberException {
    public MemberNotBorrowableException(String message) {
        super(ErrorCode.MEMBER_NOT_BORROWABLE, message);
    }
}
