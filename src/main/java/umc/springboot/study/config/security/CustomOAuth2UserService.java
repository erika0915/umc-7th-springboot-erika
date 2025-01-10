package umc.springboot.study.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.enums.Gender;
import umc.springboot.study.domain.enums.MemberStatus;
import umc.springboot.study.domain.enums.Role;
import umc.springboot.study.repository.MemberRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 카카오에서 제공하는 사용자 정보를 OAuth2User 객체로 받아온다
        OAuth2User oAuth2User = super.loadUser(userRequest);

        Map<String, Object> attributes = oAuth2User.getAttributes();
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");

        String nickname = (String) properties.get("nickname");
        String email = nickname + "@kakao.com"; // 임시 이메일 설정

        // 사용자 정보 저장 또는 업데이트
        Member member = saveOrUpdateUser(email, nickname);

        // 이메일을 Principal로 사용하기 위해 attributes 수정
        Map<String, Object> modifiedAttributes = new HashMap<>(attributes);
        modifiedAttributes.put("email", email);

        return new DefaultOAuth2User(
                oAuth2User.getAuthorities(),
                modifiedAttributes,
                "email" // email Principal로 설정
        );
    }

    private Member saveOrUpdateUser(String email, String nickname){
        Member member = memberRepository.findByEmail(email)
                .orElse(Member.builder()
                        .email(email) // 고유한 이메일
                        .name(nickname) // 닉네임을 이름으로 사용
                        .nickname(nickname) // 닉네임 저장
                        .password(passwordEncoder.encode("OAUTH_USER_" + UUID.randomUUID())) // 더미 비밀번호 생성
                        .gender(Gender.MALE) // 기본값: MALE
                        .address("소셜로그인") // 기본 주소
                        .specAddress("소셜로그인") // 기본 상세 주소
                        .status(MemberStatus.ACTIVE) // 기본 상태 설정
                        .phoneNum("010-0000-0000")
                        .role(Role.USER) // 기본 권한 설정
                        .point(0) // 기본 포인트 설정
                        .build());
        return memberRepository.save(member);
    }
}
