package me.my_library_system.domain.book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookInfoRepository extends JpaRepository<BookInfo, Long> {
    @Override
    Optional<BookInfo> findById(Long aLong);
}
