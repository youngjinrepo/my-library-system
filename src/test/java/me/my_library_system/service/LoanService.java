package me.my_library_system.service;

import me.my_library_system.domain.BookItem;
import me.my_library_system.repository.BookItemRepository;
import me.my_library_system.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoanService {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private BookItemRepository bookItemRepository;

    void loanTest() {

    }
}
