package com.delivery.service.api.domain.user.converter;

import com.delivery.service.api.common.annotation.Converter;
import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.api.domain.user.controller.model.UserRegisterRequest;
import com.delivery.service.api.domain.user.controller.model.UserResponse;
import com.delivery.service.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@Converter
public class UserConverter {
    public UserEntity toEntity(UserRegisterRequest request) {
        return Optional.ofNullable(request)
                .map(it -> {
                    return UserEntity.builder()
                            .name(request.getName())
                            .email(request.getEmail())
                            .password(request.getPassword())
                            .address(request.getAddress())
                            .build();
                })
                .orElseThrow(
                        () -> new ApiException(ApiResponseCode.NULL_POINT, "User Register to entity Null Point")
                );
    }

    public UserResponse toResponse(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    return UserResponse.builder()
                            .id(userEntity.getId())
                            .name(userEntity.getName())
                            .email(userEntity.getEmail())
                            .address(userEntity.getAddress())
                            .status(userEntity.getStatus())
                            .registeredAt(userEntity.getRegisteredAt())
                            .unregisteredAt(userEntity.getUnregisteredAt())
                            .lastLoginAt(userEntity.getLastLoginAt())
                            .build();
                })
                .orElseThrow(
                        () -> new ApiException(ApiResponseCode.NULL_POINT, "User Register to response Null Point")
                );

    }
}
