package umc.springboot.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionIdAndIsComplete(Long memberId, Long missionId, boolean isComplete);
    Page<MemberMission> findAllByMember(Member member, PageRequest pageRequest);
}

