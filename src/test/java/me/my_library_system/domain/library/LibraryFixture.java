package me.my_library_system.domain.library;

import me.my_library_system.domain.loan.LoanPolicy;
import me.my_library_system.domain.reservation.ReservationPolicy;

public class LibraryFixture {

    public static Library defaultLibrary() {
        return new Library(1L, "1", "2",
                new ReservationPolicy(5, 3),
                new LoanPolicy(5,7, 3),
                new OperatingSchedule()
        );
    }
}
