package umc.springboot.study.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.springboot.study.apiPayload.ApiResponse;
import umc.springboot.study.converter.MemberMissionConverter;
import umc.springboot.study.converter.MissionConverter;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.mapping.MemberMission;
import umc.springboot.study.service.MemberMissionService.MemberMissionCommandService;
import umc.springboot.study.service.MissionService.MissionCommandService;
import umc.springboot.study.web.dto.MissionRequestDTO;
import umc.springboot.study.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;
    private final MemberMissionCommandService memberMissionCommandService;

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
}
