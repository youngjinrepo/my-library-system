package me.my_library_system.adapter.in.web.reservation.request;

import jakarta.validation.constraints.NotNull;
import me.my_library_system.application.port.in.reservation.command.ReserveBookCommand;

public record ReserveRequest(
        @NotNull(message = "도서 정보 ID는 필수입니다.")
        Long bookInfoId,

        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId
        ) {
    public ReserveBookCommand toCommand() {
        return new ReserveBookCommand(bookInfoId, memberId);
    }
}
