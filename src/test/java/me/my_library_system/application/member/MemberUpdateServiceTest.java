package me.my_library_system.application.member;

import me.my_library_system.domain.member.Member;
import me.my_library_system.domain.member.MemberGrade;
import me.my_library_system.fixture.LoanFixture;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberUpdateServiceTest {

    @Autowired
    private MemberUpdateService memberUpdateService;
    @Autowired
    private LoanFixture loanFixture;

    @Test
    void findById() {
        Member member = loanFixture.createMember();
        memberUpdateService.promoteMember(member.getId(), "123");

        assertThat(member.getGrade()).isEqualTo(MemberGrade.REGULAR);
        assertThat(member.getCi()).isNotEmpty();
    }
}