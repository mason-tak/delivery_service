package com.delivery.service.api.domain.user.business;

import com.delivery.service.api.common.annotation.Business;
import com.delivery.service.api.domain.token.business.TokenBusiness;
import com.delivery.service.api.domain.token.controller.model.TokenResponse;
import com.delivery.service.api.domain.user.controller.model.UserLoginRequest;
import com.delivery.service.api.domain.user.controller.model.UserRegisterRequest;
import com.delivery.service.api.domain.user.controller.model.UserResponse;
import com.delivery.service.api.domain.user.converter.UserConverter;
import com.delivery.service.api.domain.user.model.User;
import com.delivery.service.api.domain.user.service.UserService;
import com.delivery.service.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Business
public class UserBusiness {
    private final UserService userService;
    private final UserConverter userConverter;
    private final TokenBusiness tokenBusiness;


    // 사용자에 대한 가입 처리 로직
    public UserResponse register(UserRegisterRequest request) {
        UserEntity entity = userConverter.toEntity(request);  // request -> entity
        UserEntity newEntity = userService.register(entity);  // entity -> save
        return userConverter.toResponse(newEntity);// save Entity -> response

        // 람다식으로 변환
//        return Optional.ofNullable(request)
//                .map(userConverter::toEntity)
//                .map(userService::register)
//                .map(userConverter::toResponse)
//                .orElseThrow(
//                        () -> new ApiException(ApiResponseCode.NULL_POINT, "user register controller null point")
//                );
    }

    public TokenResponse login(UserLoginRequest request) {
        UserEntity entity = userService.login(request.getEmail(), request.getPassword());

        return tokenBusiness.issueToken(entity);
    }

    public UserResponse me(User user) {
        UserEntity userEntity = userService.getUserWithThrow(user.getId());
        return userConverter.toResponse(userEntity);
    }
}
