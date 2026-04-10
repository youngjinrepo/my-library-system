package me.my_library_system.application.book;

import me.my_library_system.domain.book.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
@Transactional
@SpringBootTest
class BookCatalogingServiceTest {

    @Autowired
    BookCatalogingService bookCatalogingService;
    @Autowired
    BookInfoRepository bookInfoRepository;

    @Test
    void catalogingTest() {
        BookInfo harryPotter = BookFixture.createBookInfo();
        bookInfoRepository.save(harryPotter);

        bookCatalogingService.processCataloging(harryPotter.getId(), "843", 3);

        harryPotter = bookInfoRepository.findById(harryPotter.getId()).orElse(null);

        assertThat(harryPotter).isNotNull();
        assertThat(harryPotter.getId()).isNotNull();

        List<BookItem> harryPotterV1s = harryPotter.getBookItems();
        assertThat(harryPotterV1s.size()).isEqualTo(3);

        harryPotterV1s.forEach(System.out::println);

        harryPotterV1s.forEach(
                bookItem -> {
                    assertThat(bookItem.getCallNo().isEmpty()).isEqualTo(false);
                    assertThat(bookItem.getCode().isEmpty()).isEqualTo(false);
                });
    }

    @Test
    void catalogingFailTest() {
        BookInfo harryPotter = BookFixture.createBookInfo();
        bookInfoRepository.save(harryPotter);

        assertThatThrownBy(() -> {harryPotter.cataloging("dummy", 0, 100, "??");})
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shelvingTest() {
        BookInfo harryPotter = BookFixture.createBookInfo();
        bookInfoRepository.save(harryPotter);

        bookCatalogingService.processCataloging(harryPotter.getId(), "843",  3);
        harryPotter = bookInfoRepository.findById(harryPotter.getId()).orElse(null);

        bookCatalogingService.processShelving(harryPotter.getId(), "1층 종합자료실");

        assertThat(harryPotter.getStatus()).isEqualTo(BookInfoStatus.COMPLETED);
        harryPotter.getBookItems().forEach(bookItem -> {
            assertThat(bookItem.getStatus()).isEqualTo(BookItemStatus.SHELVING);
        });
    }
}