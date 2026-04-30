package me.my_library_system.domain.reservation;

public record ReservationPolicy(int maxReservationCnt) {

    public void validateMaxReservationCnt(int reservationCnt) {

    }
}
