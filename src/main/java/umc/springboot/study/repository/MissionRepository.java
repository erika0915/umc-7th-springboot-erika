package umc.springboot.study.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.study.domain.Member;
import umc.springboot.study.domain.Mission;
import umc.springboot.study.domain.Review;
import umc.springboot.study.domain.Store;

public interface MissionRepository extends JpaRepository<Mission, Long> {
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}
