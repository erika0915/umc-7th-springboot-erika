package umc.springboot.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.springboot.study.domain.enums.MissionStatus;
import umc.springboot.study.repository.MemberMissionRepository;
import umc.springboot.study.validation.annotation.NotAlreadyChallenging;

@Component
@RequiredArgsConstructor
public class MissionChallengingValidator implements ConstraintValidator<NotAlreadyChallenging, Long> {

    private final MemberMissionRepository memberMissionRepository;
    private static Long memberId;

    public static void setMemberId(Long id){
        memberId=id;
    }

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context){
        if(missionId == null || memberId== null) return false;

        // 이미 진행 중인 미션이 있는지 확인
        boolean exists = memberMissionRepository.existsByMemberIdAndMissionIdAndIsComplete(
                memberId, missionId, false
        );
        return !exists;
    }
}
