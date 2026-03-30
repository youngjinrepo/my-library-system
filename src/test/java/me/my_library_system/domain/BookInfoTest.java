package me.my_library_system.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BookInfoTest {

    @BeforeEach
    void setUp() {

    }

    @Test
    void createTest() {
        BookInfo harryPotter = BookInfo.acquireBook("해리 포터와 마법사의 돌 1", "1권", "J.K. 롤링 저자", "문학수첩", "9791193790403", false);

        assertThat(harryPotter).isNotNull();
        assertThat(harryPotter.getTitle()).isEqualTo("해리 포터와 마법사의 돌 1");
    }


}