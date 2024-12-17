package umc.springboot.study.web.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class ReviewRequestDTO {
    // 리뷰 추가 요청 DTO
    @Getter
    public static class AddReviewDTO{
        @NotNull(message = "회원 ID는 필수 입력 항목입니다.")
        private Long userId;
        @NotNull(message = "가게 ID는 필수 입력 항목입니다.")
        private Long storeId;
        @NotBlank(message = "리뷰 내용은 필수 입력 항목입니다.")
        private String content;
        @Min(value = 1, message = "평점은 최소 1점입니다.")
        @Max(value = 5, message = "평점은 최대 5점입니다.")
        private Float score;
    }
}
