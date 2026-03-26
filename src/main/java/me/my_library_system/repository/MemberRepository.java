package me.my_library_system.repository;

import me.my_library_system.domain.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberRepository extends JpaRepository<BookInfo, Long> {

    List<BookInfo> findAll();
}
