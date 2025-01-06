package umc.springboot.study.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.springboot.study.apiPayload.code.status.ErrorStatus;
import umc.springboot.study.apiPayload.exception.handler.RegionHandler;
import umc.springboot.study.converter.StoreConverter;
import umc.springboot.study.domain.Region;
import umc.springboot.study.domain.Store;
import umc.springboot.study.repository.RegionRepository;
import umc.springboot.study.repository.StoreRepository.StoreRepository;
import umc.springboot.study.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService{

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store addStore(StoreRequestDTO.AddStoreDTO request){
        // 가게를 지역에 추가하는 요청을 처리하는 메서드
        Region region = regionRepository.findById(request.getRegionId())
                .orElseThrow(()-> new RegionHandler(ErrorStatus.REGION_NOT_FOUND));
        Store newStore = StoreConverter.toStore(request, region);
        return storeRepository.save(newStore);
    }
}
