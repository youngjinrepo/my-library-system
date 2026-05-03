package me.my_library_system.adapter.in.web;

import lombok.extern.slf4j.Slf4j;
import me.my_library_system.adapter.in.web.dto.ErrorResponse;
import me.my_library_system.common.exception.BusinessException;
import me.my_library_system.common.exception.EntityNotFoundException;
import me.my_library_system.common.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /*
    * 비즈니스 규칙 위반
    * */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorCode ec = e.getErrorCode();
        log.warn("[{}] {}", ec.getCode(), e.getMessage());
        return ResponseEntity.status(ec.getStatus())
                .body(new ErrorResponse(ec.getCode(), e.getMessage()));
    }

    /*
    * 리소스 없음 404
    * */
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntityNotFoundException(EntityNotFoundException e) {
        ErrorCode ec = e.getErrorCode();
        log.warn("[{}] {}", ec.getCode(), e.getMessage());
        return ResponseEntity.status(ec.getStatus())
                .body(new ErrorResponse(ec.getCode(), e.getMessage()));
    }

    /*
    * 입력 검증 실패 (Bean Validation)
    * */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult().getFieldErrors().stream()
                .findFirst()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .orElse(ErrorCode.INVALID_INPUT.getDefaultMessage());
        log.warn("Validation failed: {}", message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(ErrorCode.INVALID_INPUT.getCode(), message));
    }

    /*
    * 그 외 예외 - 절대 클라이언트에 stack trace 노출 X
    * */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("Unexpected error", e);
        ErrorCode ec = ErrorCode.INTERNAL_SERVER_ERROR;
        return  ResponseEntity.status(ec.getStatus())
                .body(new ErrorResponse(ec.getCode(), ec.getDefaultMessage()));
    }

}
