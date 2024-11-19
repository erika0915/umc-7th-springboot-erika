package umc.springboot.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.springboot.study.domain.Review;
import umc.springboot.study.domain.Store;

import java.util.List;
import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    List<Store> findStoresByNameAndScore(String name, Float score);
    Page<Review> getReviewList(Long StoreId, Integer page);
}
