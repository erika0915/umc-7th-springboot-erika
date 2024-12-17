package umc.springboot.study.converter;

import umc.springboot.study.domain.FoodCategory;
import umc.springboot.study.domain.mapping.MemberFood;

import java.util.List;
import java.util.stream.Collectors;

public class MemberPreferConverter {

    public static List<MemberFood> toMemberFoodList(List<FoodCategory> foodCategoryList){

        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberFood.builder()
                                .foodCategory(foodCategory)
                                .build()
                ).collect(Collectors.toList());
    }
}
