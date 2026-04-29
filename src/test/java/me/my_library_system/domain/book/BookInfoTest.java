package me.my_library_system.domain.book;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class BookInfoTest {

    @Test
    void createTest() {
        BookInfo harryPotter = BookFixture.createBookInfo();
        assertThat(harryPotter).isNotNull();
        assertThat(harryPotter.getStatus()).isEqualTo(BookInfoStatus.DRAFT);
    }

    @Test
    void catalogingTest() {
        BookInfo harryPotter = BookFixture.createBookInfo();
        harryPotter.cataloging("800", 3, 1, "CD");
        assertThat(harryPotter.getStatus()).isEqualTo(BookInfoStatus.CATALOGING);
        assertThat(harryPotter.getBookItems()).hasSize(3);
    }

    @Test
    void shelvingTest() {
        BookInfo harryPotter = BookFixture.createCatalogingBookInfo();
        harryPotter.shelve("1층");
        assertThat(harryPotter.getStatus()).isEqualTo(BookInfoStatus.COMPLETED);
        harryPotter.getBookItems()
                .forEach(bookItem -> assertThat(bookItem.getStatus()).isEqualTo(BookItemStatus.SHELVING));

    }

    @Test
    void 삭제_가능_정상() {
        assertThatCode(BookFixture.createBookInfo()::validateRemovable).doesNotThrowAnyException();
    }

    @Test
    void 삭제_가능_실패() {
        BookInfo bookInfo = BookFixture.createShelvingBookInfo();
        bookInfo.getBookItems().add(BookFixture.createLoanBookItem());
        assertThatThrownBy(() -> bookInfo.validateRemovable())
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("대출중인 도서가 있을 경우 서지 정보를 제거 할 수 없습니다.");
    }

}