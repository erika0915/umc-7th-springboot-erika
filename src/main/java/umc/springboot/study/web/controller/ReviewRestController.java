package umc.springboot.study.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.springboot.study.apiPayload.ApiResponse;
import umc.springboot.study.converter.ReviewConverter;
import umc.springboot.study.domain.Review;
import umc.springboot.study.service.ReviewService.ReviewCommandService;
import umc.springboot.study.web.dto.ReviewRequestDTO;
import umc.springboot.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/add")
    public ApiResponse<ReviewResponseDTO.AddReviewResultDTO> addReview(
            @Validated @RequestBody ReviewRequestDTO.AddReviewDTO request){

        Review review = reviewCommandService.addReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toAddReviewResultDTO(review));
    }
}
