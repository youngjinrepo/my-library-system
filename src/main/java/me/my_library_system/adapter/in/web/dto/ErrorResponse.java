package me.my_library_system.adapter.in.web.dto;

import java.time.LocalDateTime;

public record ErrorResponse (String code, String message, LocalDateTime timestamp) {
    public ErrorResponse(String code, String message) {
        this(code,message,LocalDateTime.now());
    }
}
