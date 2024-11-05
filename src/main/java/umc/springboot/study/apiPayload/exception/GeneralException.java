package umc.springboot.study.apiPayload.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import umc.springboot.study.apiPayload.code.BaseErrorCode;
import umc.springboot.study.apiPayload.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public class GeneralException extends RuntimeException{

    private BaseErrorCode code;

    public ErrorReasonDTO getErrorReason(){
        return this.code.getReason();
    }

    public ErrorReasonDTO getErrorRReasonHttpStatus(){
        return this.code.getReasonHttpStatus();
    }

}
