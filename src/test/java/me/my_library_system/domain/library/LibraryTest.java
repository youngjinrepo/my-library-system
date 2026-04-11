package me.my_library_system.domain.library;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
class LibraryTest {

    @Test
    void testLibrary() {
        Library library = new Library(1L, "뽀식이네", "LDL", LibraryFixture.creatrPolicy());

        assertThat(library).isNotNull();
    }
}