package me.my_library_system.domain.member;

import me.my_library_system.fixture.LoanFixture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private LoanFixture loanFixture;

    @Test
    void findMemberById() {
        Member member = loanFixture.createMember();
        memberRepository.save(member);

        assertThat(memberRepository.findById(1L).isPresent()).isTrue();
        assertThat(memberRepository.findById(1L).get().getId()).isEqualTo(1L);
    }
}