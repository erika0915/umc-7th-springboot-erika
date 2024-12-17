package umc.springboot.study.apiPayload.exception.handler;

import umc.springboot.study.apiPayload.code.BaseErrorCode;
import umc.springboot.study.apiPayload.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
