package umc.springboot.study.converter;

import org.springframework.security.crypto.password.PasswordEncoder;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.enums.Gender;
import umc.springboot.study.web.dto.MemberRequestDTO;
import umc.springboot.study.web.dto.MemberResponseDTO;

import java.util.ArrayList;

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
}
