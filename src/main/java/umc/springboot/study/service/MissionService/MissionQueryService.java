package umc.springboot.study.service.MissionService;

import org.springframework.data.domain.Page;
import umc.springboot.study.domain.Mission;

public interface MissionQueryService {
    Page<Mission> getMissionList(Long storeId, Integer page);
}
