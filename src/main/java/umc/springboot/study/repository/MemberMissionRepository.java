package umc.springboot.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    boolean existsByMemberIdAndMissionIdAndIsComplete(Long memberId, Long missionId, boolean isComplete);
}

