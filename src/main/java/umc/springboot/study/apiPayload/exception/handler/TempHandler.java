package umc.springboot.study.apiPayload.exception.handler;

import umc.springboot.study.apiPayload.code.BaseErrorCode;
import umc.springboot.study.apiPayload.exception.GeneralException;

public class TempHandler extends GeneralException {

    public TempHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
