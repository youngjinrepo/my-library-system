package me.my_library_system.domain.member;

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
        member.promoteMember(member, "cicici");
        assertThat(member).isNotNull();
        assertThat(member.getGrade()).isEqualTo(MemberGrade.REGULAR);
        assertThat(member.getCi()).isNotEmpty();
    }

    @Test
    void promoteMemberFail() {
        Member regularMember = MemberFixture.createRegularMember();
        assertThatThrownBy(() -> regularMember.promoteMember(regularMember, "ci"))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Cannot modify member");
    }

    @Test
    void 멤버_승격_CI_없으면_실패() {
        Member associateMember = MemberFixture.createAssociateMember();
        assertThatThrownBy(()->associateMember.promoteMember(associateMember, ""))
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