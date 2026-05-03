package me.my_library_system.domain.member.exception;

import me.my_library_system.common.exception.ErrorCode;

public class MemberSuspendedException extends MemberException {
    public MemberSuspendedException(String message) {
        super(ErrorCode.MEMBER_NOT_BORROWABLE);
    }
}
