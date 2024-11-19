package umc.springboot.study.apiPayload.exception.handler;

import umc.springboot.study.apiPayload.code.BaseErrorCode;
import umc.springboot.study.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {

    public FoodCategoryHandler(BaseErrorCode errorCode){
        super(errorCode);
    }
}
