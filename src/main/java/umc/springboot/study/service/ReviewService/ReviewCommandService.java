package umc.springboot.study.service.ReviewService;

import umc.springboot.study.web.dto.ReviewRequestDTO;
import umc.springboot.study.web.dto.ReviewResponseDTO;

public interface ReviewCommandService {
    ReviewResponseDTO.ReviewPreviewDTO addReview(ReviewRequestDTO.AddReviewDTO request);
}
