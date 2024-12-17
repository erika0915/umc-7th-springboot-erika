package umc.springboot.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import umc.springboot.study.validation.annotation.ExistRegion;

public class StoreRequestDTO {

    @Getter
    public static class AddStoreDTO{
        @ExistRegion
        private Long regionId;
        @NotBlank(message = "가게 이름은 필수 입력 항목입니다.")
        private String name;
        @NotBlank(message = "주소는 필수 입력 항목입니다.")
        private String address;
    }
}
