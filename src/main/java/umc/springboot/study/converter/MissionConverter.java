package umc.springboot.study.converter;

import org.springframework.data.domain.Page;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.Store;
import umc.springboot.study.web.dto.MissionRequestDTO;
import umc.springboot.study.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

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

    public static MissionResponseDTO.MissionPreviewDTO missionPreviewDTO(Mission mission){
        // Mission 엔티티를 받아 missinoPreviewDTO 변환
        return MissionResponseDTO.MissionPreviewDTO.builder()
                .storeName(mission.getStore().getName())
                .content(mission.getContent())
                .reward(mission.getReward())
                .build();
    }

    public static MissionResponseDTO.MissionPreviewListDTO missionPreviewListDTO(Page<Mission> missionList){
        // Page<Mission>를 missionPreviewListDTO로 변환
        // 미션 리스트를 각 각 DTO로 변환
        List<MissionResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionList.stream()
                .map(MissionConverter::missionPreviewDTO).collect(Collectors.toList());
        // 변환된 데이터를 기반으로 MissionPreviewListDTO 생성
        return MissionResponseDTO.MissionPreviewListDTO.builder()
                .isFirst(missionList.isFirst())
                .isLast(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .missionList(missionPreviewDTOList)
                .build();
    }
}
