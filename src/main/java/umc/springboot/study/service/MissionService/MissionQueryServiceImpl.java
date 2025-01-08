package umc.springboot.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.springboot.study.apiPayload.code.status.ErrorStatus;
import umc.springboot.study.apiPayload.exception.GeneralException;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.Store;
import umc.springboot.study.repository.MissionRepository;
import umc.springboot.study.repository.StoreRepository.StoreRepository;

@Service
@RequiredArgsConstructor
public class MissionQueryServiceImpl implements MissionQueryService{

    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        return missionRepository.findAllByStore(store, PageRequest.of(page,10));
    }
}
