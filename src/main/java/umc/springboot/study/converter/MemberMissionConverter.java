package umc.springboot.study.converter;

import org.springframework.data.domain.Page;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.enums.MissionStatus;
import umc.springboot.study.domain.mapping.MemberMission;
import umc.springboot.study.web.dto.MemberResponseDTO;
import umc.springboot.study.web.dto.MissionRequestDTO;
import umc.springboot.study.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(MissionRequestDTO.ChallengeMissionDTO request, Member member, Mission mission) {
        // 미션 진행 중 요청을 받음 MissionRequestDTO.ChallengeMissionDTO 을 활용하여 MemberMission 엔티티로 변환
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING) // 기본 상태를 CHALLENGING으로 설정
                .isComplete(false) // 초기값으로 미완료 상태 설정
                .build();
    }

    public static MissionResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(MemberMission memberMission) {
        // MemberMission 엔티티를 MissionResponseDTO.ChallengeMissionResultDTO로 변환
        return MissionResponseDTO.ChallengeMissionResultDTO.builder()
                .memberId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .isChallenging(!memberMission.isComplete()) // isComplete의 반대로 설정해야함 !
                .build();
    }

    public static MemberResponseDTO.MissionPreviewDTO toMissionPreviewDTO(MemberMission memberMission) {
        // MemberMission 엔티티를 MissionResponse.MissonPreviewDTO로 변환
        return MemberResponseDTO.MissionPreviewDTO.builder()
                .content(memberMission.getMission().getContent())
                .reward(memberMission.getMission().getReward())
                .storeName(memberMission.getMission().getStore().getName())
                .missionStatus(memberMission.getStatus().name()) // enum 값을 문자열로 반환
                .isComplete(memberMission.isComplete())
                .build();
    }

    public static MemberResponseDTO.MissionPreviewListDTO toMissionPreviewListDTO(Page<MemberMission> missionList){
        // Page<MemberMission> 를 toMissionPreviewListDTO 로 변환
        // 미션 리스트를 각 각 DTO로 변환
        List<MemberResponseDTO.MissionPreviewDTO> missionPreviewDTOList = missionList.stream()
                .map(MemberMissionConverter::toMissionPreviewDTO)
                .collect(Collectors.toList());

        // 변환된 데이터를 기반으로 ReviewPreviewListDTO 생성
        return MemberResponseDTO.MissionPreviewListDTO.builder()
                .missionList(missionPreviewDTOList)
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalElements(missionList.getTotalElements())
                .totalPage(missionList.getTotalPages())
                .missionList(missionPreviewDTOList)
                .build();
    }
}