package me.my_library_system.service;

import me.my_library_system.domain.BookInfo;
import me.my_library_system.repository.BookInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        BookInfo harryPotter = BookInfo.acquireBook("해리 포터와 마법사의 돌 1", "1권", "J.K. 롤링 저자", "문학수첩", "9791193790403", false);
        bookInfoRepository.save(harryPotter);

        bookCatalogingService.processCataloging(harryPotter.getId(), "843", "1층 종합자료실", 3);

        harryPotter = bookInfoRepository.findById(harryPotter.getId()).orElse(null);

        assertThat(harryPotter).isNotNull();
        assertThat(harryPotter.getId()).isNotNull();
        assertThat(harryPotter.getBookItems().size()).isEqualTo(3);
    }
}