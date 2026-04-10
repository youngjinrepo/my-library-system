package me.my_library_system.domain.member;

import java.util.ArrayList;

public class MemberFixture {
    public static Member createAssociateMember() {
        return Member.createMember("김테스터", "kim.tester@name.com", "남", 10, "집 주소");
    }

    public static Member createRegularMember() {
        Member regularMember = createAssociateMember();
        Member.promoteMember(regularMember, "test|test|test");
        regularMember.setLoans(new ArrayList<>());
        return regularMember;
    }
}
