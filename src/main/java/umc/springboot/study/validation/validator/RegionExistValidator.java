package umc.springboot.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springboot.study.apiPayload.code.status.ErrorStatus;
import umc.springboot.study.repository.RegionRepository;
import umc.springboot.study.validation.annotation.ExistRegion;

@Component
@RequiredArgsConstructor
public class RegionExistValidator implements ConstraintValidator<ExistRegion, Long> {

    private final RegionRepository regionRepository;

    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context){
        if(value==null){
            return false;
        }

        boolean exists = regionRepository.existsById(value);

        if(!exists){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.REGION_NOT_FOUND.getMessage())
                    .addConstraintViolation();
        }
        return exists;
    }
}
