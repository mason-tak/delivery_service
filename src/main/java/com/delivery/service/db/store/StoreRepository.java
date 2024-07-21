package com.delivery.service.db.store;

import com.delivery.service.db.store.enums.StoreCategory;
import com.delivery.service.db.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity, Long> {
    // 유효한 스토어
    // select * from store where id = ? and status = ? order by id desc limit 1;
    Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus storeStatus);

    // 유효한 전체 스토어 관리
    // select * from store where status = ? order by id desc;
    List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus storeStatus);

    // 유효한 특정 카테고리 전체 스토어 리스트
    // select * from store where status = ? and category = ? order by id desc;
    List<StoreEntity> findAllByStatusAndCategoryOrderByIdDesc(StoreStatus storeStatus, StoreCategory storeCategory);
}
