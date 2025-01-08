package umc.springboot.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.springboot.study.apiPayload.ApiResponse;
import umc.springboot.study.converter.MemberConverter;
import umc.springboot.study.converter.MemberMissionConverter;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.mapping.MemberMission;
import umc.springboot.study.service.MemberMissionService.MemberMissionQueryService;
import umc.springboot.study.validation.annotation.ValidPage;
import umc.springboot.study.web.dto.MemberResponseDTO;

@RestController
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberMissionRestController {

    private final MemberMissionQueryService memberMissionQueryService;
    @GetMapping("/{memberId}/missions")
    @Operation(summary = "특정 사용자가 진행 중인 미션 목록 조회 API",
            description = "특정 사용자가 진행 중인 미션 목록을 조회하는 API이며, 페이징을 포함한다. query string으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청")
    })

    @Parameters({
            @Parameter(name="memberId", description = "사용자의 아이디, path variable 입니다."),
            @Parameter(name="page", description = "페이지 번호, query string 입니다.")
    })

    public ApiResponse<MemberResponseDTO.MissionPreviewListDTO> getChallengingMissionList(
            @PathVariable(name="memberId") Long memberId,
            @RequestParam(name="page", defaultValue = "1") @ValidPage Integer page){
        Page<MemberMission> memberMission  = memberMissionQueryService.getChallengingMissionList(memberId, page-1);
        return ApiResponse.onSuccess(MemberMissionConverter.toMissionPreviewListDTO(memberMission));
    }

}
