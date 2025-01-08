package umc.springboot.study.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.springboot.study.apiPayload.code.status.ErrorStatus;
import umc.springboot.study.apiPayload.exception.GeneralException;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.mapping.MemberMission;
import umc.springboot.study.repository.MemberMissionRepository;
import umc.springboot.study.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;
    @Override
    public Page<MemberMission> getChallengingMissionList(Long memberId, Integer page){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));
        return memberMissionRepository.findAllByMember(member, PageRequest.of(page,10));
    }
}
