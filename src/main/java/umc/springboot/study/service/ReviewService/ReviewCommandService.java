package umc.springboot.study.service.ReviewService;

import umc.springboot.study.domain.Review;
import umc.springboot.study.web.dto.ReviewRequestDTO;

public interface ReviewCommandService {
    Review addReview(ReviewRequestDTO.AddReviewDTO request);
}
