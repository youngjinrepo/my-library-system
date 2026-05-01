package me.my_library_system.domain.library;

import me.my_library_system.domain.reservation.ReservationPolicy;

public class LibraryFixture {

    public static Library defaultLibrary() {
        return new Library(1L, "1", "2", creatrPolicy(),
                new ReservationPolicy(5, 3));
    }
    public static Policy creatrPolicy() {
        return new Policy(1,2,3, 4, 5);
    }

    public static Policy creatrBadPolicy() {
        return new Policy(0,0,0, 0,  0);
    }
}
