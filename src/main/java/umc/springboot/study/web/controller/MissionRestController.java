package umc.springboot.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.springboot.study.apiPayload.ApiResponse;
import umc.springboot.study.converter.MemberMissionConverter;
import umc.springboot.study.converter.MissionConverter;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.mapping.MemberMission;
import umc.springboot.study.service.MemberMissionService.MemberMissionCommandService;
import umc.springboot.study.service.MissionService.MissionCommandService;
import umc.springboot.study.service.MissionService.MissionQueryService;
import umc.springboot.study.validation.annotation.ExistStore;
import umc.springboot.study.validation.annotation.ValidPage;
import umc.springboot.study.web.dto.MissionRequestDTO;
import umc.springboot.study.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/missions")
@Validated
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MemberMissionCommandService memberMissionCommandService;
    private final MissionQueryService missionQueryService;
    @PostMapping("/add")
    @Operation(summary = "특정 가게에 미션 추가하기 API")
    public ApiResponse<MissionResponseDTO.AddMissionResultDTO> addMission(
            @RequestBody @Valid MissionRequestDTO.AddMissionDTO request){
        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.onSuccess(MissionConverter.toAddMissionResultDTO(mission));
    }

    @PostMapping("/challenge")
    @Operation(summary = " 가게의 미션을 도전 중인 미션에 추가하기 API")
    public ApiResponse<MissionResponseDTO.ChallengeMissionResultDTO> challengeMission(
        @RequestBody @Valid MissionRequestDTO.ChallengeMissionDTO request) {
        MemberMission memberMission = memberMissionCommandService.challengeMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toChallengeMissionResultDTO(memberMission));
    }

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게의 리뷰들의 목록을 조회하는 API이며, 페이징을 초함한다. query string으로 page 번호를 주세요.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description="OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON400", description = "잘못된 요청")
    })

    @Parameters({
            @Parameter(name="storeId", description = "가게의 아이디, path variable 입니다.")
    })

    public ApiResponse<MissionResponseDTO.MissionPreviewListDTO> getMissionList(
            @ExistStore @PathVariable(name="storeId") Long storeId,
            @RequestParam(name="page", defaultValue = "1") @ValidPage Integer page){
        Page<Mission> missionList = missionQueryService.getMissionList(storeId, page-1);
        return ApiResponse.onSuccess(MissionConverter.missionPreviewListDTO(missionList));
    }
}
