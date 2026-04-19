package me.my_library_system.domain.member;

import java.time.LocalDateTime;

public class MemberFixture {
    public static Member createAssociateMember() {
        return Member.createMember("김테스터", "kim.tester@name.com", "남", 10, "집 주소");
    }

    public static Member createRegularMember() {
        Member regularMember = createAssociateMember();
        regularMember.promoteMember(regularMember, "test|test|test");
        return regularMember;
    }

    public static Member createSuspendedMember() {
        Member suspendedMember = createRegularMember();
        suspendedMember.suspend(3);
        return suspendedMember;
    }
}
