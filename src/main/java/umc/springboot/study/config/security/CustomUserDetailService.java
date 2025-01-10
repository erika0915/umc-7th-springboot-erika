package umc.springboot.study.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import umc.springboot.study.domain.Member;
import umc.springboot.study.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 이메일을 기준으로 DB에서 사용자 검색
        Member member = memberRepository.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("해당 이메일을 가진 유저가 존재하지 않습니다: " + username));

        System.out.println("로그인 시도: " + member.getEmail()); // 디버깅용 로그
        System.out.println("비밀번호: " + member.getPassword());
        System.out.println("역할: " + member.getRole().name());

        // UserDetails 객체 반환
        return org.springframework.security.core.userdetails.User
                .withUsername(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().name())
                .build();
    }
}
