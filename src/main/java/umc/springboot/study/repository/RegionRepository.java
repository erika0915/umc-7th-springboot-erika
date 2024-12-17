package umc.springboot.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.study.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
