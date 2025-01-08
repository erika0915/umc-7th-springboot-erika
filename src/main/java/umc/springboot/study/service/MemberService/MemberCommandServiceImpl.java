package umc.springboot.study.service.MemberService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.springboot.study.converter.MemberConverter;
import umc.springboot.study.converter.MemberPreferConverter;
import umc.springboot.study.domain.FoodCategory;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.mapping.MemberFood;
import umc.springboot.study.repository.FoodCategoryRepository;
import umc.springboot.study.repository.MemberRepository;
import umc.springboot.study.web.dto.MemberRequestDTO;
import umc.springboot.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.springboot.study.apiPayload.code.status.ErrorStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request){
        // 회원가입 요청을 처리하는 joinMember 메서드

        // Member 엔티티 생성
        Member newMember = MemberConverter.toMember(request);

        // 비밀번호 암호화
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        // 선호 카테고리 id 리스트로 FoodCategory 리스트를 조회
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(categoryId->{
                    return foodCategoryRepository.findById(categoryId).
                            orElseThrow(()-> new FoodCategoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());

        // FoodCategory 리스트를 MemberFood 리스트로 변환하고 Member 설정
        List<MemberFood> memberFoodList = MemberPreferConverter.toMemberFoodList(foodCategoryList);
        memberFoodList.forEach(memberFood -> {memberFood.setMember(newMember);});
        return memberRepository.save(newMember);
    }
}
