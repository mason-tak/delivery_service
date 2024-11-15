package com.delivery.service.api.domain.storemenu.converter;

import com.delivery.service.api.common.annotation.Converter;
import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import com.delivery.service.api.domain.storemenu.controller.model.StoreMenuResponse;
import com.delivery.service.db.storemenu.StoreMenuEntity;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Converter
public class StoreMenuConverter {
    public StoreMenuEntity toEntity(StoreMenuRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return StoreMenuEntity.builder()
                            .storeId(request.getStoreId())
                            .name(request.getName())
                            .amount(request.getAmount())
                            .thumbnailUrl(request.getThumbnail())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }

    public StoreMenuResponse toResponse(StoreMenuEntity storeMenuEntity) {
        return Optional.ofNullable(storeMenuEntity)
                .map(it -> {
                    return StoreMenuResponse.builder()
                            .id(storeMenuEntity.getId())
                            .storeId(storeMenuEntity.getStoreId())
                            .amount(storeMenuEntity.getAmount())
                            .status(storeMenuEntity.getStatus())
                            .thumbnail(storeMenuEntity.getThumbnailUrl())
                            .likeCount(storeMenuEntity.getLikeCount())
                            .sequence(storeMenuEntity.getSequence())
                            .build();
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));

    }

    public List<StoreMenuResponse> toResponse(
            List<StoreMenuEntity> list
    ) {
        return list.stream()
                .map(it -> toResponse(it))
                .collect(Collectors.toList());
    }
}
