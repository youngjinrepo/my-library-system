package me.my_library_system.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
    void promoteMemberFail() {
        assertThatThrownBy(() -> Member.promoteMember(MemberFixture.createRegularMember(), "ci"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Cannot modify member");
    }

    @Test
    void 멤버_승격_CI_없으면_실패() {
        assertThatThrownBy(()->Member.promoteMember(MemberFixture.createAssociateMember(), ""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("CI 값은 비어있으면 안됩니다.");
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