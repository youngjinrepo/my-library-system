package me.my_library_system.adapter.out.persistence.jpa;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookItemJpaRepositoryTest {

    @Autowired
    private BookItemJpaRepository bookItemJpaRepository;

    @Test
    void 책ID로_서지정보를_조회(){
         bookItemJpaRepository.findByBookInfoId(1L);
    }
}