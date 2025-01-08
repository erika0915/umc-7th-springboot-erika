package umc.springboot.study.service.StoreService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.springboot.study.apiPayload.code.status.ErrorStatus;
import umc.springboot.study.apiPayload.exception.GeneralException;
import umc.springboot.study.domain.Review;
import umc.springboot.study.domain.Store;
import umc.springboot.study.repository.ReviewRepository;
import umc.springboot.study.repository.StoreRepository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly=true)
public class StoreQueryServiceImpl implements StoreQueryService{

    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public Optional<Store> findStore(Long id){
        return storeRepository.findById(id);
    }

    @Override
    public List<Store> findStoresByNameAndScore(String name, Float score){
        List<Store> filteredStores = storeRepository.dynamicQueryWithBooleanBuilder(name, score);
        filteredStores.forEach(store -> System.out.println("Store: " + store));
        return filteredStores;
    }

    @Override
    public Page<Review> getReviewList(Long StoreId, Integer page){
        Store store = storeRepository.findById(StoreId).
                orElseThrow(()-> new GeneralException(ErrorStatus.STORE_NOT_FOUND));
        return reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
    }
}
