package me.my_library_system.domain.member;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String sex;
    private int age;
    private String address;
    private String ci;
    private MemberGrade grade;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public static Member createMember(String name, String email, String sex, int age, String address) {
        Member member = new Member();
        member.name = name;
        member.email = email;
        member.sex = sex;
        member.age = age;
        member.address = address;
        member.createdAt = LocalDateTime.now();
        member.grade = MemberGrade.ASSOCIATE;
        return member;
    }

    public void promoteMember(Member member, String ci) {
        if (member.grade!=MemberGrade.ASSOCIATE) {
            throw new IllegalStateException("Cannot modify member");
        }
        if (ci == null || ci.trim().isEmpty()) {
            throw new IllegalArgumentException("CI 값은 비어있으면 안됩니다.");
        }
        member.ci = ci;
        member.updateAt = LocalDateTime.now();
        member.grade = MemberGrade.REGULAR;
    }

    public boolean canBorrow() {
        if (this.grade!=MemberGrade.REGULAR) {
            throw new IllegalStateException("대출 불가한 이용자 입니다.");
        }
        return true;
    }
}
