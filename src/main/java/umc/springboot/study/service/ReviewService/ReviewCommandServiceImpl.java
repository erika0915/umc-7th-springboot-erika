package umc.springboot.study.service.ReviewService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import umc.springboot.study.apiPayload.code.status.ErrorStatus;
import umc.springboot.study.apiPayload.exception.GeneralException;
import umc.springboot.study.apiPayload.exception.handler.StoreHandler;
import umc.springboot.study.converter.ReviewConverter;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Review;
import umc.springboot.study.domain.Store;
import umc.springboot.study.repository.MemberRepository;
import umc.springboot.study.repository.ReviewRepository;
import umc.springboot.study.repository.StoreRepository.StoreRepository;
import umc.springboot.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService{

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review addReview(ReviewRequestDTO.AddReviewDTO request){

        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(()-> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));

        Member member = memberRepository.findById(request.getUserId())
                .orElseThrow(()-> new GeneralException(ErrorStatus.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, store, member);
        return reviewRepository.save(review);
    }
}
