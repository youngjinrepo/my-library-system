package me.my_library_system.service;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.BookInfo;
import me.my_library_system.domain.CustomSequence;
import me.my_library_system.repository.BookInfoRepository;
import me.my_library_system.repository.CustomSequenceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookCatalogingService {

    private final BookInfoRepository bookInfoRepository;
    private final CustomSequenceRepository sequenceRepository;

    @Transactional
    public void processCataloging(Long bookId, String classify, String location, int bookCnt) {
        BookInfo bookInfo = bookInfoRepository.findById(bookId).orElseThrow();
        CustomSequence customSequence = sequenceRepository.findByNameWithLock("BOOK_CODE")
                .orElseGet(() -> sequenceRepository.save(new CustomSequence("BOOK_CODE", 0)));

        int startSeq = customSequence.getNextSequence(bookCnt);

        bookInfo.cataloging(classify, location, bookCnt, startSeq);
    }
}
