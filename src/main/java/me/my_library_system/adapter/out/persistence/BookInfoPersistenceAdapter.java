package me.my_library_system.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.out.persistence.jpa.BookInfoJapRepository;
import me.my_library_system.domain.book.BookInfo;
import me.my_library_system.domain.book.BookInfoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookInfoPersistenceAdapter implements BookInfoRepository {
    private final BookInfoJapRepository bookInfoJapRepository;

    @Override
    public Optional<BookInfo> findById(Long id) {
        return bookInfoJapRepository.findById(id);
    }

    @Override
    public BookInfo save(BookInfo bookInfo) {
        return bookInfoJapRepository.save(bookInfo);
    }

    @Override
    public void deleteById(Long bookInfoId) {
        bookInfoJapRepository.deleteById(bookInfoId);
    }
}
