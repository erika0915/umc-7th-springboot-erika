package umc.springboot.study.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.springboot.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.springboot.study.domain.FoodCategory;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access= AccessLevel.PROTECTED)
@AllArgsConstructor
public class MemberFood extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="food_id")
    private FoodCategory foodCategory;

    public void setMember(Member member){
        if(this.member != null)
            member.getMemberFoodList().remove(this);

        this.member = member;
        member.getMemberFoodList().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory){
        this.foodCategory = foodCategory;
    }
}
