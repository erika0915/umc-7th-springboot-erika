package umc.springboot.study.service.MemberMissionService;

import org.springframework.data.domain.Page;
import umc.springboot.study.domain.mapping.MemberMission;

public interface MemberMissionQueryService {
    Page<MemberMission> getChallengingMissionList(Long memberId, Integer page);
}
