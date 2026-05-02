package me.my_library_system.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.out.persistence.jpa.BookItemJpaRepository;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.book.BookItemRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookItemPersistenceAdapter implements BookItemRepository {
    private final BookItemJpaRepository bookItemJpaRepository;

    @Override
    public Optional<BookItem> findById(Long id) {
        return bookItemJpaRepository.findById(id);
    }

    @Override
    public void save(BookItem bookItem) {
        bookItemJpaRepository.save(bookItem);
    }

    @Override
    public List<BookItem> findByBookInfoId(Long bookInfoId) {
        return bookItemJpaRepository.findByBookInfoId(bookInfoId);
    }
}
