package umc.springboot.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springboot.study.apiPayload.code.status.ErrorStatus;
import umc.springboot.study.domain.Store;
import umc.springboot.study.service.StoreService.StoreQueryService;
import umc.springboot.study.validation.annotation.ExistStore;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class StoreExistValidator implements ConstraintValidator<ExistStore, Long> {

    private final StoreQueryService storeQueryService;

    @Override
    public void initialize(ExistStore constraintAnnotation){
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context){
        Optional<Store> target=storeQueryService.findStore(value);

        if(target.isEmpty()){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.STORE_NOT_FOUND.getMessage())
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
