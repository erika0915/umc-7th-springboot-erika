package umc.springboot.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberMission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false)
    private boolean isComplete;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "mission_id")
    private Mission mission;

}
