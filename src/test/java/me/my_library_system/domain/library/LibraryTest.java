package me.my_library_system.domain.library;

import me.my_library_system.domain.reservation.ReservationPolicy;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
class LibraryTest {

    @Test
    void testLibrary() {
        Library library = new Library(1L, "뽀식이네", "LDL", LibraryFixture.creatrPolicy(), new ReservationPolicy(1,2));

        assertThat(library).isNotNull();
    }
}