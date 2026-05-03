package me.my_library_system.application.member;

import lombok.RequiredArgsConstructor;
import me.my_library_system.common.exception.EntityNotFoundException;
import me.my_library_system.domain.member.Member;
import me.my_library_system.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberUpdateService {
    private final MemberRepository memberRepository;

    @Transactional
    public void promoteMember(Long id, String ci) {
        Member member = memberRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Member", id));
        member.promoteMember(member, ci);
        memberRepository.save(member);
    }
}
