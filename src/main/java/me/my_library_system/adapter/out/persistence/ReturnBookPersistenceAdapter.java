package me.my_library_system.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.out.persistence.jpa.ReturnJpaRepository;
import me.my_library_system.domain.returnBook.ReturnBook;
import me.my_library_system.domain.returnBook.ReturnBookRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ReturnBookPersistenceAdapter implements ReturnBookRepository {

    private final ReturnJpaRepository returnJpaRepository;

    @Override
    public void save(ReturnBook returnBook) {
        returnJpaRepository.save(returnBook);
    }
}
