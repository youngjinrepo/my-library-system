package me.my_library_system.domain.book;

import java.util.List;
import java.util.Optional;

public interface BookItemRepository{
    Optional<BookItem> findById(Long Id);

    void save(BookItem bookItem);

    List<BookItem> findByBookInfoId(Long bookInfoId);
}
