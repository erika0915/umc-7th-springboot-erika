package umc.springboot.study.service.MemberMissionService;

import umc.springboot.study.domain.mapping.MemberMission;
import umc.springboot.study.web.dto.MissionRequestDTO;


public interface MemberMissionCommandService {
    MemberMission challengeMission(MissionRequestDTO.ChallengeMissionDTO request);
}
