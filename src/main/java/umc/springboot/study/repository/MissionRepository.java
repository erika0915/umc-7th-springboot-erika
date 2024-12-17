package umc.springboot.study.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.study.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long> {
}
