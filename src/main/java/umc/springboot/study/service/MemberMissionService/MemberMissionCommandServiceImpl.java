package umc.springboot.study.service.MemberMissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.springboot.study.apiPayload.code.status.ErrorStatus;
import umc.springboot.study.apiPayload.exception.GeneralException;
import umc.springboot.study.converter.MemberMissionConverter;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.mapping.MemberMission;
import umc.springboot.study.repository.MemberMissionRepository;
import umc.springboot.study.repository.MemberRepository;
import umc.springboot.study.repository.MissionRepository;
import umc.springboot.study.web.dto.MissionRequestDTO;
import umc.springboot.study.web.dto.MissionResponseDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;

    @Override
    @Transactional
    public MemberMission challengeMission(MissionRequestDTO.ChallengeMissionDTO request){

        Long memberId = request.getMemberId();
        Long missionId = request.getMissionId();

        // 중복 도전 여부 검증
        boolean alreadyChallenging = memberMissionRepository.
                existsByMemberIdAndMissionIdAndIsComplete(memberId, missionId, false);

        if(alreadyChallenging){
            throw new GeneralException(ErrorStatus.ALREADY_CHALLENGING);
        }

        // 회원 및 미션 조회
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(()-> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Mission mission = missionRepository.findById(request.getMissionId())
                .orElseThrow(()-> new GeneralException(ErrorStatus.MISSION_NOT_FOUND));

        MemberMission memberMission = MemberMissionConverter.toMemberMission(request, member, mission);
        return memberMissionRepository.save(memberMission);
    }
}
