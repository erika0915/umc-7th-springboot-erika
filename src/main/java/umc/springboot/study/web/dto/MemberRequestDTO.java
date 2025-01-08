package umc.springboot.study.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.springboot.study.domain.enums.Role;
import umc.springboot.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
    @Setter // thymeleaf에서 사용하지 위해서 추가
    public static class JoinDTO{

        @NotBlank
        String name;
        @NotBlank
        String nickname;
        @NotNull
        Integer gender;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @Email
        String email;
        @NotBlank
        String password;
        @NotNull
        Role role;
        @NotNull
        String phoneNum;
        @ExistCategories
        List<Long> preferCategory;
    }
}
