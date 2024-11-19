package umc.springboot.study.web.dto;

import lombok.Getter;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    public static class JoinDTO{
        String name;
        String nickname;
        Integer gender;
        String address;
        String specAddress;
        String email;
        String phoneNum;
        List<Long> preferCategory;
    }
}
