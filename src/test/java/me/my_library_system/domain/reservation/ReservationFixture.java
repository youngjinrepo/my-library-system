package me.my_library_system.domain.reservation;

import static me.my_library_system.domain.reservation.Reservation.*;

public class ReservationFixture {


    public static Reservation normalReservation() {
        return createReservation(1L, 1L);
    }

    public static Reservation canceledReservation() {
        Reservation reservation = createReservation(2L, 1L);
        reservation.canceledReservation();
        return reservation;
    }
}
