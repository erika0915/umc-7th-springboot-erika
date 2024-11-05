package umc.springboot.study.converter;

import umc.springboot.study.web.dto.TempResponse;

public class TempConverter {

    public static TempResponse.TempTestDto toTempTestDto(){
        return TempResponse.TempTestDto.builder()
                .testString("this is test!")
                .build();
    }

    public static TempResponse.TempExceptionDTO toTempExceptionDTO(Integer flag){
        return TempResponse.TempExceptionDTO.builder()
                .flag(flag)
                .build();
    }
}
