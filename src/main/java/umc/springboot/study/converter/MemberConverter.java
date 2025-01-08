package umc.springboot.study.converter;

import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.Review;
import umc.springboot.study.domain.enums.Gender;
import umc.springboot.study.domain.mapping.MemberMission;
import umc.springboot.study.web.dto.MemberRequestDTO;
import umc.springboot.study.web.dto.MemberResponseDTO;
import umc.springboot.study.web.dto.MissionResponseDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        // Member 엔티티 객체를 받아 MemberResponseDTO.JoinResultDTO 로 변환
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request){
        // 회원가입 요청으로 받은 MemberRequestDTO.JoinDTO 를 Member 엔티티로 변환
        Gender gender = null;
        switch (request.getGender()){
            case 1:
                gender =Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
        }
        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .nickname(request.getNickname())
                .phoneNum(request.getPhoneNum())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .memberFoodList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review){
        // Review 엔티티를 받아 reviewPreviewDTO 변환
        return MemberResponseDTO.ReviewPreviewDTO.builder()
                .nickname(review.getMember().getNickname())
                .score(review.getScore())
                .content(review.getContent())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviewList){
        // Page<Review>를 reviewPreviewListDTO로 변환

        // 리뷰 리스트를 각 각 DTO로 변환
        List<MemberResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(MemberConverter::reviewPreviewDTO).collect(Collectors.toList());

        // 변환된 데이터를 기반으로 ReviewPreviewListDTO 생성
        return MemberResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .reviewList(reviewPreviewDTOList)
                .build();
    }

    public static MemberResponseDTO.MissionPreviewDTO missionPreviewDTO(MemberMission memberMission){
        // MemberMission 엔티티를 받아 toMissionPreviewDTO로 변환
        return MemberResponseDTO.MissionPreviewDTO.builder()
                .content(memberMission.getMission().getContent())
                .storeName(memberMission.getMission().getStore().getName())
                .reward(memberMission.getMission().getReward())
                .missionStatus(memberMission.getStatus().name())
                .isComplete(memberMission.isComplete())
                .build();
    }

    public static MemberResponseDTO.MissionPreviewListDTO missionPreviewListDTO(Page<MemberMission> memberMissionList){
        // 미션 리스트를 각 각 DTO로 변환
        List<MemberResponseDTO.MissionPreviewDTO> missionPreviewDTOList = memberMissionList.stream()
                .map(MemberConverter::missionPreviewDTO).collect(Collectors.toList());

        // 변환된 데이터를 기반으로 MissionPreviewListDTO 생성
        return MemberResponseDTO.MissionPreviewListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .missionList(missionPreviewDTOList)
                .build();
    }
}
