package me.my_library_system.service;

import me.my_library_system.domain.BookInfo;
import me.my_library_system.domain.enums.BookInfoStatus;
import me.my_library_system.domain.BookItem;
import me.my_library_system.domain.enums.BookItemStatus;
import me.my_library_system.repository.BookInfoRepository;
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


    @BeforeEach
    void setUp() {

    }

    @Test
    void catalogingTest() {
        BookInfo harryPotter = BookInfo.acquireBook("해리 포터와 마법사의 돌 1", "1권", "J.K. 롤링 저자", "문학수첩", "9791193790403", false);
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
        BookInfo harryPotter = BookInfo.acquireBook("해리 포터와 마법사의 돌 1", "1권", "J.K. 롤링 저자", "문학수첩", "9791193790403", false);
        bookInfoRepository.save(harryPotter);

        assertThatThrownBy(() -> {harryPotter.cataloging("dummy", 0, 100);})
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void shelvingTest() {
        BookInfo harryPotter = BookInfo.acquireBook("해리 포터와 마법사의 돌 1", "1권", "J.K. 롤링 저자", "문학수첩", "9791193790403", false);
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