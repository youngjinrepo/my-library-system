package me.my_library_system.domain;

import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
import me.my_library_system.domain.book.BookItem;
import me.my_library_system.domain.enums.MemberStatus;

import java.time.LocalDateTime;
import java.util.List;

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
    @OneToMany(fetch = FetchType.LAZY)
    private List<Loan> loans;

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

    public boolean canBorrow() {
        if (this.status!=MemberStatus.REGULAR) {
            throw new IllegalStateException("대출 불가한 이용자 입니다.");
        }
        return true;
    }
}
