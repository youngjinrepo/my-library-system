package me.my_library_system.adapter.out.persistence;

import lombok.RequiredArgsConstructor;
import me.my_library_system.adapter.out.persistence.jpa.MemberJapRepository;
import me.my_library_system.domain.member.Member;
import me.my_library_system.domain.member.MemberRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class MemberPersistenceAdapter implements MemberRepository {
    private final MemberJapRepository memberJapRepository;

    @Override
    public Optional<Member> findById(Long id) {
        return memberJapRepository.findById(id);
    }

    @Override
    public Member save(Member member) {
        return memberJapRepository.save(member);
    }

    @Override
    public void flush() {
        memberJapRepository.flush();
    }
}
