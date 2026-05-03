package me.my_library_system.application.book;

import lombok.RequiredArgsConstructor;
import me.my_library_system.common.exception.EntityNotFoundException;
import me.my_library_system.domain.library.Library;
import me.my_library_system.domain.book.BookInfo;
import me.my_library_system.adapter.out.persistence.sequnce.CustomSequence;
import me.my_library_system.domain.book.BookInfoRepository;
import me.my_library_system.adapter.out.persistence.sequnce.CustomSequenceRepository;
import me.my_library_system.domain.library.LibraryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookCatalogingService {

    private final BookInfoRepository bookInfoRepository;
    private final CustomSequenceRepository sequenceRepository;
    private final LibraryRepository libraryRepository;

    @Transactional
    public void processCataloging(Long bookId, String classify, int bookCnt) {
        BookInfo bookInfo = bookInfoRepository.findById(bookId).orElseThrow(()->new EntityNotFoundException("BookInfo", bookId));
        CustomSequence customSequence = sequenceRepository.findByNameWithLock("BOOK_CODE")
                .orElseGet(() -> sequenceRepository.save(new CustomSequence("BOOK_CODE", 0)));

        int startSeq = customSequence.getNextSequence(bookCnt);

        Library library = libraryRepository.getLibrary();
        bookInfo.cataloging(classify, bookCnt, startSeq, library.getCode());
        bookInfoRepository.save(bookInfo);
    }

    @Transactional
    public void processShelving(Long bookId, String location){
        BookInfo bookInfo = bookInfoRepository.findById(bookId).orElseThrow(() -> new EntityNotFoundException("BookInfo", bookId));
        bookInfo.shelve(location);
        bookInfoRepository.save(bookInfo);
    }

    @Transactional
    public void processRemove(Long bookInfoId) {
        BookInfo bookInfo = bookInfoRepository.findById(bookInfoId).orElseThrow(() ->  new EntityNotFoundException("BookInfo", bookInfoId));
        bookInfo.validateRemovable();
        bookInfoRepository.deleteById(bookInfoId);
    }
}
