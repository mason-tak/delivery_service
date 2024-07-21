package com.delivery.service.api.domain.store.service;

import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.db.store.StoreEntity;
import com.delivery.service.db.store.StoreRepository;
import com.delivery.service.db.store.enums.StoreCategory;
import com.delivery.service.db.store.enums.StoreStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreService {
    private final StoreRepository storeRepository;

    // 유효한 스토어 가져오기
    public StoreEntity getStoreWithThrow(Long id) {
        // 디버그 포인트 찍어보려고 할때
//        Optional<StoreEntity> entity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED);
//        return entity.orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));

        return storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED)
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }

    // 스토어 등록
    public StoreEntity register(StoreEntity storeEntity) {
        return Optional.ofNullable(storeEntity)
                .map(it -> {
                    storeEntity.setStar(0);
                    storeEntity.setStatus(StoreStatus.REGISTERED);

                    return storeRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }

    // 카테고리로 스토어 검색
    public List<StoreEntity> searchByCategory(StoreCategory storeCategory) {
        return storeRepository.findAllByStatusAndCategoryOrderByIdDesc(StoreStatus.REGISTERED, storeCategory);

    }

    // 전체 스토어
    public List<StoreEntity> registerStore() {
        return storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
    }
}
