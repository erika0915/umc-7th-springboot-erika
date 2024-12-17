package umc.springboot.study.service.MissionService;

import umc.springboot.study.domain.Mission;
import umc.springboot.study.web.dto.MissionRequestDTO;

public interface MissionCommandService{
    Mission addMission(MissionRequestDTO.AddMissionDTO request);
}
