package me.my_library_system.application.book;

import me.my_library_system.adapter.out.persistence.sequnce.CustomSequence;
import me.my_library_system.adapter.out.persistence.sequnce.CustomSequenceRepository;
import me.my_library_system.domain.book.*;
import me.my_library_system.domain.book.exception.IllegalBookStateException;
import me.my_library_system.domain.book.exception.InvalidBookInputException;
import me.my_library_system.domain.library.Library;
import me.my_library_system.domain.library.LibraryFixture;
import me.my_library_system.domain.library.LibraryRepository;
import me.my_library_system.domain.reservation.ReservationPolicy;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

class BookCatalogingServiceTest {

    BookInfoRepository bookInfoRepository = mock(BookInfoRepository.class);
    CustomSequenceRepository customSequenceRepository = mock(CustomSequenceRepository.class);
    LibraryRepository libraryRepository = mock(LibraryRepository.class);
    BookCatalogingService bookCatalogingService = new BookCatalogingService(bookInfoRepository, customSequenceRepository, libraryRepository);

    Library library = LibraryFixture.defaultLibrary();

    @Test
    void 편목_작업_성공() {
        given(bookInfoRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.createBookInfo()));
        given(customSequenceRepository.findByNameWithLock("BOOK_CODE"))
                .willReturn(Optional.of(new CustomSequence("BOOK_CODE", 0)));

        given(libraryRepository.getLibrary())
                .willReturn(library);

        bookCatalogingService.processCataloging(1L, "843", 3);

        then(bookInfoRepository).should(times(1)).save(any(BookInfo.class));
    }

    @Test
    void 편목_실패_서지정보_이상() {
        given(bookInfoRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.emptyBookInfo()));
        given(customSequenceRepository.findByNameWithLock("BOOK_CODE"))
                .willReturn(Optional.of(new CustomSequence("BOOK_CODE", 0)));
        given(libraryRepository.getLibrary())
                .willReturn(library);

        assertThatThrownBy(() -> bookCatalogingService.processCataloging(1L, "843", 3))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("Book ID is null");
    }

    @Test
    void 편목_실패_도서상태_이상(){
        given(bookInfoRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.createShelvingBookInfo()));
        given(customSequenceRepository.findByNameWithLock("BOOK_CODE"))
                .willReturn(Optional.of(new CustomSequence("BOOK_CODE", 0)));
        given(libraryRepository.getLibrary())
                .willReturn(library);

        assertThatThrownBy(() -> bookCatalogingService.processCataloging(1L, "843", 3))
                .isInstanceOf(IllegalBookStateException.class);
    }

    @Test
    void 편목_실패_도서갯수_이상(){
        given(bookInfoRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.createBookInfo()));
        given(customSequenceRepository.findByNameWithLock("BOOK_CODE"))
                .willReturn(Optional.of(new CustomSequence("BOOK_CODE", 0)));
        given(libraryRepository.getLibrary())
                .willReturn(library);

        assertThatThrownBy(() -> bookCatalogingService.processCataloging(1L, "843", 0))
                .isInstanceOf(InvalidBookInputException.class);
    }

    @Test
    void 배가_성공() {
        given(bookInfoRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.createCatalogingBookInfo()));

        bookCatalogingService.processShelving(1L, "1층 종합자료실");

        then(bookInfoRepository).should(times(1)).save(any(BookInfo.class));
    }

    @Test
    void 배가_실패_도서상태_이상() {
        given(bookInfoRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.createBookInfo()));
        assertThatThrownBy(() -> bookCatalogingService.processShelving(1L, "1층 종합자료실"))
                .isInstanceOf(IllegalBookStateException.class);
    }

    @Test
    void 배가_실패_매개변수_이상() {
        given(bookInfoRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.createCatalogingBookInfo()));
        assertThatThrownBy(() -> bookCatalogingService.processShelving(1L, ""))
                .isInstanceOf(InvalidBookInputException.class);
    }

    @Test
    void 도서_삭제_테스트() {
        given(bookInfoRepository.findById(1L))
                .willReturn(Optional.of(BookFixture.createShelvingBookInfo()));

        bookCatalogingService.processRemove(1L);
    }
}