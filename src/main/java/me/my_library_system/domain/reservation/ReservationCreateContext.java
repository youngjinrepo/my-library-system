package me.my_library_system.domain.reservation;

public record ReservationCreateContext(boolean hasSameReserve, int reservationCnt, int cancellationCnt, int overdueCnt) {
}
