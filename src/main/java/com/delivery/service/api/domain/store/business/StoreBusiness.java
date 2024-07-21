package com.delivery.service.api.domain.store.business;

import com.delivery.service.api.common.annotation.Business;
import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.api.domain.store.controller.model.StoreRegisterRequest;
import com.delivery.service.api.domain.store.controller.model.StoreResponse;
import com.delivery.service.api.domain.store.converter.StoreConverter;
import com.delivery.service.api.domain.store.service.StoreService;
import com.delivery.service.db.store.StoreEntity;
import com.delivery.service.db.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Business
@Slf4j
public class StoreBusiness {
    private final StoreService storeService;
    private final StoreConverter storeConverter;

    public StoreResponse register(StoreRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(storeConverter::toEntity)
                .map(storeService::register)
                .map(storeConverter::toResponse)
                .orElseThrow(
                        () -> new ApiException(ApiResponseCode.NULL_POINT, "store register business null point")
                );
    }

    public List<StoreResponse> searchCategory(StoreCategory storeCategory) {
        // entity list -> response list
        List<StoreEntity> storeList = storeService.searchByCategory(storeCategory);

        return storeList.stream()
                .map(storeConverter::toResponse)
                .collect(Collectors.toList());
    }
}
