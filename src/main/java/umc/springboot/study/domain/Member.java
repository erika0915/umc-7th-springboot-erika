package umc.springboot.study.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.springboot.study.domain.common.BaseEntity;
import umc.springboot.study.domain.enums.Gender;
import umc.springboot.study.domain.enums.MemberStatus;
import umc.springboot.study.domain.mapping.MemberFood;
import umc.springboot.study.domain.mapping.MemberMission;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@DynamicUpdate
@DynamicInsert
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, length=20)
    private String name;

    @Column(nullable = false, length=20)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10)")
    private Gender gender;

    @Column(nullable=false, length=40)
    private String address;

    @Column(nullable=false, length=40)
    private String specAddress;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(10) DEFAULT 'ACTIVE' ")
    private MemberStatus status;

    @Column(nullable=false, length=50)
    private String email;

    @Column(nullable=false, length=20)
    private String phoneNum;

    @ColumnDefault("0")
    private Integer point;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberMission> memberMissionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    // 기본값 설정
    @PrePersist
    public void prePersist(){
        if(this.status==null){
            this.status=MemberStatus.ACTIVE;
        }
    }
}
