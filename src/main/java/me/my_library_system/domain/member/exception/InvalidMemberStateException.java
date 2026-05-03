package me.my_library_system.domain.member.exception;

import me.my_library_system.common.exception.ErrorCode;

public class InvalidMemberStateException extends MemberException {

    public InvalidMemberStateException(String message) {
        super(ErrorCode.INVALID_MEMBER_STATE, message);
    }

    public InvalidMemberStateException() {
        super(ErrorCode.INVALID_MEMBER_STATE);
    }
}
