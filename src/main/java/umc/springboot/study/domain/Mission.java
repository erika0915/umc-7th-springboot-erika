package umc.springboot.study.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.springboot.study.domain.common.BaseEntity;
import umc.springboot.study.domain.enums.MissionStatus;
import umc.springboot.study.domain.mapping.MemberMission;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Mission extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Integer reward;

    @Enumerated(EnumType.STRING)
    private MissionStatus status;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="store_id")
    private Store store;

    @OneToMany(mappedBy = "mission", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
