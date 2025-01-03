package umc.springboot.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springboot.study.repository.MemberMissionRepository;
import umc.springboot.study.validation.annotation.NotAlreadyChallenging;
import umc.springboot.study.web.dto.MissionRequestDTO;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<NotAlreadyChallenging, MissionRequestDTO.ChallengeMissionDTO> {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean isValid(MissionRequestDTO.ChallengeMissionDTO request,
                           ConstraintValidatorContext context) {
        if (request.getMemberId() == null || request.getMissionId() == null){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("memberId와 missionId는 필수 값입니다.")
                    .addConstraintViolation();
            return false;
        }

        boolean alreadyChallenging = memberMissionRepository.existsByMemberIdAndMissionIdAndIsComplete(
                request.getMemberId(), request.getMissionId(), false);


        if (alreadyChallenging){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 도전 중인 미션입니다.").addConstraintViolation();
            return false;
        }
        return true;
    }
}
