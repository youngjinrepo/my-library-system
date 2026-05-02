package me.my_library_system.adapter.out.persistence.jpa;

import me.my_library_system.domain.book.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookInfoJpaRepository extends JpaRepository<BookInfo, Long> {
}
