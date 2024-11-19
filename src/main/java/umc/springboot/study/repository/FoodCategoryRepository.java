package umc.springboot.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.study.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}
