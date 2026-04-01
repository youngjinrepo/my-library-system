package me.my_library_system.repository;

import me.my_library_system.domain.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    @Test
    void createMember() {
        Member member = Member.createMember("김대기", "kim.wait@name.com", "남", 10, "ox빌라 302호");
        assertNotNull(member);
    }
}