package me.my_library_system.application.book;

import me.my_library_system.adapter.out.persistence.sequnce.CustomSequence;
import me.my_library_system.adapter.out.persistence.sequnce.CustomSequenceRepository;
import me.my_library_system.domain.book.*;
import me.my_library_system.domain.library.Library;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.library.Policy;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class BookCatalogingServiceTest {


    BookInfoRepository bookInfoRepository = Mockito.mock(BookInfoRepository.class);
    CustomSequenceRepository customSequenceRepository = Mockito.mock(CustomSequenceRepository.class);
    LibraryRepository libraryRepository = Mockito.mock(LibraryRepository.class);
    BookCatalogingService bookCatalogingService = new BookCatalogingService(bookInfoRepository, customSequenceRepository, libraryRepository);

    @Test
    void catalogingTest() {

        given(bookInfoRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.createBookInfo()));
        given(customSequenceRepository.findByNameWithLock("BOOK_CODE"))
                .willReturn(Optional.of(new CustomSequence("BOOK_CODE", 0)));
        given(libraryRepository.getLibrary())
                .willReturn(new Library(1L, "1","2", new Policy(1,2,3)));
//
//        BookInfo harryPotter = BookFixture.createBookInfo();
//        bookInfoRepository.save(harryPotter);

        bookCatalogingService.processCataloging(1L, "843", 3);
//
//        harryPotter = bookInfoRepository.findById(harryPotter.getId()).orElse(null);
//
//        assertThat(harryPotter).isNotNull();
//        assertThat(harryPotter.getId()).isNotNull();
//
//        List<BookItem> harryPotterV1s = harryPotter.getBookItems();
//        assertThat(harryPotterV1s.size()).isEqualTo(3);
//
//        harryPotterV1s.forEach(System.out::println);
//
//        harryPotterV1s.forEach(
//                bookItem -> {
//                    assertThat(bookItem.getCallNo().isEmpty()).isEqualTo(false);
//                    assertThat(bookItem.getCode().isEmpty()).isEqualTo(false);
//                });
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

    @Test
    void removeTest() {
        BookInfo harryPotter = BookFixture.createShelvingBookInfo();
        bookCatalogingService.processRemove(harryPotter.getId());
    }
}