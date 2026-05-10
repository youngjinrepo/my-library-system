package me.my_library_system.application.port.in.reservation.command;

import java.util.Objects;

public record ReserveBookCommand(Long bookInfoId, Long memberId) {

    public ReserveBookCommand {
        Objects.requireNonNull(bookInfoId, "도서 정보 ID는 필수입니다");
        Objects.requireNonNull(memberId, "도서 정보 ID는 필수입니다");
    }
}