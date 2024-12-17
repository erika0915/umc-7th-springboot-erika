package umc.springboot.study.apiPayload.exception.handler;

import umc.springboot.study.apiPayload.code.BaseErrorCode;
import umc.springboot.study.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {

    public StoreHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
