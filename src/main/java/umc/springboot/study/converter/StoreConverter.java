package umc.springboot.study.converter;

import org.springframework.data.domain.Page;
import umc.springboot.study.domain.Review;
import umc.springboot.study.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review){
        return StoreResponseDTO.ReviewPreviewDTO.builder()
                .nickname(review.getMember().getNickname())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static StoreResponseDTO.ReviewPreviewListDTO reveiwPreviewListDTO(Page<Review> reviewList){
        List<StoreResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreviewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .reviewList(reviewPreviewDTOList)
                .build();
    }
}
