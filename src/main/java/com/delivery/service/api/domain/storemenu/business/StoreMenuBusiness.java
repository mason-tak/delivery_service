package com.delivery.service.api.domain.storemenu.business;

import com.delivery.service.api.common.annotation.Business;
import com.delivery.service.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import com.delivery.service.api.domain.storemenu.controller.model.StoreMenuResponse;
import com.delivery.service.api.domain.storemenu.converter.StoreMenuConverter;
import com.delivery.service.api.domain.storemenu.service.StoreMenuService;
import com.delivery.service.db.storemenu.StoreMenuEntity;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
public class StoreMenuBusiness {
    private final StoreMenuService storeMenuService;
    private final StoreMenuConverter storeMenuConverter;

    public StoreMenuResponse register(StoreMenuRegisterRequest request) {
        // req -> entity -> save -> response
        StoreMenuEntity entity = storeMenuConverter.toEntity(request);
        StoreMenuEntity newEntity = storeMenuService.register(entity);
        StoreMenuResponse response = storeMenuConverter.toResponse(newEntity);

        return response;
    }

    public List<StoreMenuResponse> search(Long storeId) {
        List<StoreMenuEntity> list = storeMenuService.getStoreMenuByStoreId(storeId);

        return list.stream()
                .map(it -> {
                    return storeMenuConverter.toResponse(it);
                })
                .collect(Collectors.toList());
    }
}
