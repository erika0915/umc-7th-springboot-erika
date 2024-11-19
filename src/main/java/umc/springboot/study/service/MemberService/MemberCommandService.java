package umc.springboot.study.service.MemberService;

import umc.springboot.study.domain.Member;
import umc.springboot.study.web.dto.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDTO request);
}
