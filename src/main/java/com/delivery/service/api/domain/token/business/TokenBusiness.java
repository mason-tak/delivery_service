package com.delivery.service.api.domain.token.business;

import com.delivery.service.api.common.annotation.Business;
import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.api.domain.token.controller.model.TokenResponse;
import com.delivery.service.api.domain.token.converter.TokenConverter;
import com.delivery.service.api.domain.token.model.TokenDto;
import com.delivery.service.api.domain.token.service.TokenService;
import com.delivery.service.db.user.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Business
@RequiredArgsConstructor
public class TokenBusiness {
    private final TokenService tokenService;
    private final TokenConverter tokenConverter;

    public TokenResponse issueToken(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(ue -> {
                    return userEntity.getId();
                })
                .map(userId -> {
                    TokenDto accessToken = tokenService.issueAccessToken(userId);
                    TokenDto refreshToken = tokenService.issueRefreshToken(userId);

                    return tokenConverter.tokenResponse(accessToken, refreshToken);
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT, "token business issueToken null point"));

    }
}
