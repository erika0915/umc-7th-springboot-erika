package umc.springboot.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.study.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
