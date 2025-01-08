package umc.springboot.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.common.BaseEntity;
import umc.springboot.study.domain.enums.MissionStatus;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Column(nullable=false)
    private boolean isComplete;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

}
