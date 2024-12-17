package umc.springboot.study.converter;

import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.enums.MissionStatus;
import umc.springboot.study.domain.mapping.MemberMission;
import umc.springboot.study.web.dto.MissionRequestDTO;
import umc.springboot.study.web.dto.MissionResponseDTO;

public class MemberMissionConverter {

    public static MemberMission toMemberMission(MissionRequestDTO.ChallengeMissionDTO request, Member member, Mission mission){
        // 미션 진행 중 요청을 받음 MissionRequestDTO.ChallengeMissionDTO 을 활용하여 MemberMission 엔티티로 변환
        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .isComplete(false)
                .build();
    }

    public static MissionResponseDTO.ChallengeMissionResultDTO toChallengeMissionResultDTO(MemberMission memberMission){
        // MemberMission 엔티티를 MissionResponseDTO.ChallengeMissionResultDTO로 변환
        return MissionResponseDTO.ChallengeMissionResultDTO.builder()
                .memberId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .isComplete(memberMission.isComplete())
                .build();
    }
}
