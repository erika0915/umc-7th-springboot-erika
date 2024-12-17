package umc.springboot.study.converter;

import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Review;
import umc.springboot.study.domain.Store;
import umc.springboot.study.web.dto.ReviewRequestDTO;
import umc.springboot.study.web.dto.ReviewResponseDTO;

public class ReviewConverter {

    public static Review toReview(ReviewRequestDTO.AddReviewDTO request, Store store, Member member){
        // 리뷰 추가 요청을 받은 ReviewRequestDTO.AddReviewDTO 를 Review 엔티티로 변환
        return Review.builder()
                .member(member)
                .store(store)
                .score(request.getScore())
                .content(request.getContent())
                .build();
    }

    public static ReviewResponseDTO.AddReviewResultDTO toAddReviewResultDTO(Review review){
        // Review 엔티티 객체를 받아 ReviewResponseDTO.AddReviewResultDTO 로 변환
        return ReviewResponseDTO.AddReviewResultDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .userName(review.getMember().getName())
                .content(review.getContent())
                .score(review.getScore())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
