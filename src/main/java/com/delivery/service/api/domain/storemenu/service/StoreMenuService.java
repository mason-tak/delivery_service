package com.delivery.service.api.domain.storemenu.service;

import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.db.storemenu.StoreMenuEntity;
import com.delivery.service.db.storemenu.StoreMenuRepository;
import com.delivery.service.db.storemenu.enums.StoreMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreMenuService {

    private final StoreMenuRepository storeMenuRepository;

    // 메뉴를 가져오는 메서드
    public StoreMenuEntity getStoreMenuWithThrow(Long id) {
        var entity = storeMenuRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreMenuStatus.REGISTERD);
        return entity.orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }

    // store id를 가지고 찾는 메서드
    public List<StoreMenuEntity> getStoreMenuByStoreId(Long storeId) {
        return storeMenuRepository.findAllByStoreIdAndStatusOrderBySequenceDesc(storeId, StoreMenuStatus.REGISTERD);
    }

    public StoreMenuEntity register(StoreMenuEntity storeMenuEntity) {
        return Optional.ofNullable(storeMenuEntity)
                .map(it -> {
                    it.setStatus(StoreMenuStatus.REGISTERD);
                    return storeMenuRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }

}
