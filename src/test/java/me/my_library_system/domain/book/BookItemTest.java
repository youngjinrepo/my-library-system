package me.my_library_system.domain.book;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BookItemTest {

    @Test
    void 대출중인_책은_비활성화_할_수_없다() {
        BookItem bookItem = BookFixture.createLoanBookItem();
        assertThatThrownBy(() -> bookItem.unavailable())
                .isInstanceOf(IllegalStateException.class);
    }
}