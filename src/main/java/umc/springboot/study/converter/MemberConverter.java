package umc.springboot.study.converter;

import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.enums.Gender;
import umc.springboot.study.web.dto.MemberRequestDTO;
import umc.springboot.study.web.dto.MemberResponseDTO;

import java.util.ArrayList;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(member.getCreatedAt())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request){
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
                .memberFoodList(new ArrayList<>())
                .build();
    }
}
