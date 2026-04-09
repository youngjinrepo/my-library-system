package me.my_library_system.domain.member;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository{

    Optional<Member> findById(Long id);

    Member save(Member member);
}
