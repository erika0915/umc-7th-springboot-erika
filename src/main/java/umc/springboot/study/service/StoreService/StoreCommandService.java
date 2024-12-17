package umc.springboot.study.service.StoreService;

import umc.springboot.study.domain.Store;
import umc.springboot.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store addStore(StoreRequestDTO.AddStoreDTO request);
}
