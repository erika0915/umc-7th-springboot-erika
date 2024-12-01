package umc.springboot.study.web.dto;

import lombok.Getter;

public class ReviewRequestDTO {
    // 리뷰 추가 요청 DTO
    @Getter
    public static class AddReviewDTO{
        String nickname;
        Float score;
        String content;
    }
}
