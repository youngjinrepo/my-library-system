package me.my_library_system.domain.book;

import java.util.Optional;

public interface BookItemRepository{
    Optional<BookItem> findById(Long Id);
}
