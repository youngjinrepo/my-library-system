package me.my_library_system.repository;

import me.my_library_system.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;


@Service
public interface MemberRepository extends JpaRepository<Member, Long> {


}
