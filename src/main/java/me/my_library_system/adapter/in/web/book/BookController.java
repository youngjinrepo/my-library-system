package me.my_library_system.adapter.in.web.book;

import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.out.persistence.BookInfoPersistenceAdapter;
import me.my_library_system.domain.book.BookInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book/")
@RequiredArgsConstructor
public class BookController {

    private final BookInfoPersistenceAdapter bookInfoPersistenceAdapter;

    @GetMapping("{id}")
    public ResponseEntity<BookInfo> getBookInfo(@PathVariable Long id) {
        bookInfoPersistenceAdapter.findById(id);
    }
}
