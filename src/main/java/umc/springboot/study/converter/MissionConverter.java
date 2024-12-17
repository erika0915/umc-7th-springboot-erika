package umc.springboot.study.converter;

import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.Store;
import umc.springboot.study.web.dto.MissionRequestDTO;
import umc.springboot.study.web.dto.MissionResponseDTO;

public class MissionConverter {

    public static Mission toMission(MissionRequestDTO.AddMissionDTO request, Store store){
        // 미션 추가 요청을 받은 MissionRequestDTO.AddMissionDTO를 Mission 엔티티로 변환
        return Mission.builder()
                .content(request.getContent())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.AddMissionResultDTO toAddMissionResultDTO(Mission mission){
        // Mission 엔티티 객체를 받아 MissionResponseDTO.AddMissionResultDTO로 변환
        return MissionResponseDTO.AddMissionResultDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .reward(mission.getReward())
                .createdAt(mission.getCreatedAt())
                .build();
    }
}
