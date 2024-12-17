package umc.springboot.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.springboot.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {

    @Getter
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
        @NotNull
        String email;
        @NotNull
        String phoneNum;
        @ExistCategories
        List<Long> preferCategory;
    }
}
