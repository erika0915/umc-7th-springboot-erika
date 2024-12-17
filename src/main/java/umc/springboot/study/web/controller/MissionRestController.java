package umc.springboot.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.springboot.study.apiPayload.ApiResponse;
import umc.springboot.study.converter.MissionConverter;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.service.MissionService.MissionCommandService;
import umc.springboot.study.web.dto.MissionRequestDTO;
import umc.springboot.study.web.dto.MissionResponseDTO;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/add")
    public ApiResponse<MissionResponseDTO.AddMissionResultDTO> addMission(
            @RequestBody @Valid MissionRequestDTO.AddMissionDTO request){

        Mission mission = missionCommandService.addMission(request);
        return ApiResponse.onSuccess(MissionConverter.toAddMissionResultDTO(mission));
    }
}
