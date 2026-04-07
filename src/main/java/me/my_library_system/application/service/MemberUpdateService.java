package me.my_library_system.application.service;

import lombok.RequiredArgsConstructor;
import me.my_library_system.domain.Member;
import me.my_library_system.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberUpdateService {

    private final MemberRepository memberRepository;
    //private final IdentityAuthentication identityAuthentication;

    @Transactional
    public void registerMember(Long id, String ci) {
        Member member = memberRepository.findById(id).orElseThrow();
        //member.registerMember(ci);
    }
}
