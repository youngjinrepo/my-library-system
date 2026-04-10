package me.my_library_system.domain.book;

import java.util.Optional;

public interface BookInfoRepository{

    Optional<BookInfo> findById(Long id);

    BookInfo save(BookInfo bookInfo);

    void deleteById(Long bookInfoId);
}
