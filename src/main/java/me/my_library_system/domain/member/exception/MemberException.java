package me.my_library_system.domain.member.exception;

import me.my_library_system.common.exception.BusinessException;
import me.my_library_system.common.exception.ErrorCode;

public class MemberException extends BusinessException {
    protected MemberException(ErrorCode errorCode) {
        super(errorCode);
    }

    protected MemberException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }
}
