package me.my_library_system.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.*;
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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
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

    public boolean canBorrow(BookItem bookItem, LibraryPolicy policy) {
        if (this.status!=MemberStatus.REGULAR) {
            throw new IllegalStateException("대출 불가한 이용자 입니다.");
        }
        if (!bookItem.isAvailable()) {
            throw new IllegalStateException(bookItem.getCode() + " 도서는 이용 불가합니다.");
        }
        if (loans.size() < policy.getMaxLoanCnt()) {
            throw new IllegalStateException("최대 대출가능 건수를 초과했습니다.");
        }
        return true;
    }
}
