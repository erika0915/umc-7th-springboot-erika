package umc.springboot.study.web.dto;

import lombok.Getter;

public class ReviewRequestDTO {
    // 리뷰 추가 요청 DTO
    @Getter
    public static class AddReviewDTO{
        private Long userId;
        private Long storeId;
        private String content;
        private Float score;
    }
}
