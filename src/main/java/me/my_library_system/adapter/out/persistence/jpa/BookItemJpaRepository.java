package me.my_library_system.adapter.out.persistence.jpa;

import me.my_library_system.domain.book.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookItemJpaRepository extends JpaRepository<BookItem, Long> {
    List<BookItem> findByBookInfoId(Long bookInfoId);
}
