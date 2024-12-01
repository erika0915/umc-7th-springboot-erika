package umc.springboot.study.converter;

import umc.springboot.study.domain.Review;
import umc.springboot.study.domain.Store;
import umc.springboot.study.web.dto.ReviewRequestDTO;

public class ReviewConverter {

    // StoreRequestDTO.AddReviewDTO  -> 엔티티 변환
    public Review toReview(ReviewRequestDTO.AddReviewDTO request, Store store){
        return Review.builder()
                .store(store)
                .score(request.getScore())
                .content(request.getContent())
                .build();
    }
}
