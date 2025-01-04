package umc.springboot.study.converter;

import org.springframework.data.domain.Page;
import umc.springboot.study.domain.Region;
import umc.springboot.study.domain.Review;
import umc.springboot.study.domain.Store;
import umc.springboot.study.web.dto.StoreRequestDTO;
import umc.springboot.study.web.dto.StoreResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static Store toStore(StoreRequestDTO.AddStoreDTO request, Region region){
        // 가게 추가 요청을 받은 StoreRequestDTO.AddStoreDTO 를 Store 엔티티로 변환
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }

    public static StoreResponseDTO.AddStoreResultDTO toAddStoreResultDTO(Store store) {
        // Store 엔티티 객체를 받아 StoreResponseDTO.AddStoreResultDTO 로 변환
        return StoreResponseDTO.AddStoreResultDTO.builder()
                .storeId(store.getId())
                .storeName(store.getName())
                .regionName(store.getRegion().getName())
                .createdAt(store.getCreatedAt())
                .build();
    }

    public static StoreResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review){
        // Review 엔티티를 받아 reviewPreviewDTO 변환
        return StoreResponseDTO.ReviewPreviewDTO.builder()
                .nickname(review.getMember().getNickname())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static StoreResponseDTO.ReviewPreviewListDTO reveiwPreviewListDTO(Page<Review> reviewList){
        // Page<Review>를 reviewPreviewListDTO로 변환

        // 리뷰 리스트를 각 각 DTO로 변환
        List<StoreResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreviewDTO).collect(Collectors.toList());

        // 변환된 데이터를 기반으로 ReviewPreviewListDTO 생성
        return StoreResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .reviewList(reviewPreviewDTOList)
                .build();
    }
}
