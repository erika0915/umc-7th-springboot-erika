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
