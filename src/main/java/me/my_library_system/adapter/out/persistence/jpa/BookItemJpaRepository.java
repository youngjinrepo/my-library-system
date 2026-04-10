package me.my_library_system.adapter.out.persistence.jpa;

import me.my_library_system.domain.book.BookItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookItemJpaRepository extends JpaRepository<BookItem, Long> {
}
