package com.delivery.service.api.domain.store.converter;

import com.delivery.service.api.common.annotation.Converter;
import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.api.domain.store.controller.model.StoreRegisterRequest;
import com.delivery.service.api.domain.store.controller.model.StoreResponse;
import com.delivery.service.db.store.StoreEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class StoreConverter {
    public StoreEntity toEntity(StoreRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return StoreEntity.builder()
                            .name(request.getName())
                            .address(request.getAddress())
                            .category(request.getStoreCategory())
                            .thumbnailUrl(request.getThumbnailUrl())
                            .minimumAmount(request.getMinimumAmount())
                            .minimumDeliveryAmount(request.getMinimumDeliveryAmount())
                            .phoneNumber(request.getPhoneNumber())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }

    public StoreResponse toResponse(StoreEntity entity) {
        return Optional.ofNullable(entity)
                .map(it -> {
                    return StoreResponse.builder()
                            .id(entity.getId())
                            .name(entity.getName())
                            .address(entity.getAddress())
                            .status(entity.getStatus())
                            .category(entity.getCategory())
                            .thumbnailUrl(entity.getThumbnailUrl())
                            .minimumAmount(entity.getMinimumAmount())
                            .minimumDeliveryAmount(entity.getMinimumDeliveryAmount())
                            .star(entity.getStar())
                            .phoneNumber(entity.getPhoneNumber())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }
}
