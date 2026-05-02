package me.my_library_system.domain.library;

import me.my_library_system.domain.loan.LoanPolicy;
import me.my_library_system.domain.reservation.ReservationPolicy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
class LibraryTest {

    @Test
    void testLibrary() {
        Library library = new Library(1L, "뽀식이네", "LDL",

                new ReservationPolicy(1,2),
                new LoanPolicy(5,7, 3),
                new OperatingSchedule()
        );

        assertThat(library).isNotNull();
    }
}