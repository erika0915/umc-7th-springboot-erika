package umc.springboot.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne
    @JoinColumn(name="memeber_id")
    private Member member;

    @ManyToOne
    @JoinColumn
    private Mission mission;
}
