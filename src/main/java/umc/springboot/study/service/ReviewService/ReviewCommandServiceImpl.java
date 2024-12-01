package umc.springboot.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import umc.springboot.study.repository.ReviewRepository;
import umc.springboot.study.web.dto.ReviewRequestDTO;
import umc.springboot.study.web.dto.ReviewResponseDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public ReviewResponseDTO.ReviewPreviewDTO addReview(ReviewRequestDTO.AddReviewDTO request){
        return null;
    }
}
