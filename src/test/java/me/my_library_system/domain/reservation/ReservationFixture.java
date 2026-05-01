package me.my_library_system.domain.reservation;

public class ReservationFixture {


    public static Reservation normalReservation() {
        return Reservation.createReservation(1L, 10L,
                new ReservationPolicy(3, 5),
                new ReservationCreateContext(false, 0, 0, 0));
    }

    public static Reservation canceledReservation() {
//        Reservation reservation = createReservation(2L, 1L);
//        reservation.canceledReservation();
//        return reservation;
        return  null;
    }
}
