package me.my_library_system.domain.member;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MemberTest {

    @Test
    void createMember() {
        Member member = MemberFixture.createAssociateMember();
        assertThat(member).isNotNull();
        assertThat(member.getGrade()).isEqualTo(MemberGrade.ASSOCIATE);
    }

    @Test
    void promoteMember() {
        Member member = MemberFixture.createAssociateMember();
        Member.promoteMember(member, "cicici");
        assertThat(member).isNotNull();
        assertThat(member.getGrade()).isEqualTo(MemberGrade.REGULAR);
        assertThat(member.getCi()).isNotEmpty();
    }

    @Test
    void canBorrow() {
        Member member = MemberFixture.createRegularMember();
        assertThat(member.canBorrow()).isTrue();
    }

    @Test
    void canBorrowException() {
        Member member = MemberFixture.createAssociateMember();
        assertThatThrownBy(() -> member.canBorrow()).isInstanceOf(IllegalStateException.class);
    }
}