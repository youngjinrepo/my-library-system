package me.my_library_system.domain.member.exception;

import me.my_library_system.common.exception.ErrorCode;

public class InvalidMemberInput extends MemberException {

    public InvalidMemberInput(String message) {
        super(ErrorCode.INVALID_MEMBER_INPUT,  message);
    }
}
