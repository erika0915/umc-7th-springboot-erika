package umc.springboot.study.repository.StoreRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.springboot.study.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
