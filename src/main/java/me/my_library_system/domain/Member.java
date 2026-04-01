package me.my_library_system.domain;

import lombok.*;
import me.my_library_system.domain.enums.MemberStatus;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
public class Member {
    private Long id;
    private String name;
    private String email;
    private String sex;
    private int age;
    private String address;
    private String ci;
    private String grade;
    private MemberStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updateAt;
    private LocalDateTime deleteAt;

    public static Member createMember(String name, String email, String sex, int age, String address) {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setSex(sex);
        member.setAge(age);
        member.setAddress(address);
        member.setCreatedAt(LocalDateTime.now());
        member.setStatus(MemberStatus.ASSOCIATE);
        return member;
    }
}
