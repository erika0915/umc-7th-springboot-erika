package umc.springboot.study.web.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDateTime;

public class MissionRequestDTO {

    @Getter
    public static class AddMissionDTO{

        @NotNull(message="미션 내용은 필수 입력 항목입니다.")
        private String content;
        @NotNull(message = "보상은 필수 입력 항목입니다.")
        private Integer reward;
        @NotNull(message = "storeId는 필수 입력 항목입니다.")
        private Long storeId;
        @NotNull(message = "마감 기한은 필수 입력 항목입니다.")
        @Future(message = "마감 기한은 미래의 날짜여야 합니다.")
        private LocalDateTime deadline;
    }
}
