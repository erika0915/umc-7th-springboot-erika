package umc.springboot.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.springboot.study.apiPayload.code.status.ErrorStatus;
import umc.springboot.study.apiPayload.exception.handler.StoreHandler;
import umc.springboot.study.converter.MissionConverter;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.Store;
import umc.springboot.study.repository.MissionRepository;
import umc.springboot.study.repository.StoreRepository.StoreRepository;
import umc.springboot.study.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService{

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission addMission(MissionRequestDTO.AddMissionDTO request){
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(()-> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(request,store);
        return missionRepository.save(mission);
    }
}
