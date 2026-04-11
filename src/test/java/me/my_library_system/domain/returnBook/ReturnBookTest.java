package me.my_library_system.domain.returnBook;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReturnBookTest {

    @Test
    void 반납_생성() {
        Assertions.assertThat(ReturnFixture.createReturnBook());
    }

}