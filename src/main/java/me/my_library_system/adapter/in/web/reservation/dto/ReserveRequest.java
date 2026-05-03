package me.my_library_system.adapter.in.web.reservation.dto;

import jakarta.validation.constraints.NotNull;

public record ReserveRequest(
        @NotNull(message = "도서 정보 ID는 필수입니다.")
        Long bookInfoId,

        @NotNull(message = "회원 ID는 필수입니다.")
        Long memberId
        ) {
}
