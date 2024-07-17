package com.delivery.service.api.domain.token.converter;

import com.delivery.service.api.common.annotation.Converter;
import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.api.domain.token.controller.model.TokenResponse;
import com.delivery.service.api.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;

import java.util.Objects;

@Converter
@RequiredArgsConstructor
public class TokenConverter {
    public TokenResponse tokenResponse(TokenDto accessToken, TokenDto refreshToken) {
        // access token, refresh token 값 null 인지 체큰
        Objects.requireNonNull(accessToken, () -> {
            throw new ApiException(ApiResponseCode.NULL_POINT);
        });
        Objects.requireNonNull(refreshToken, () -> {
            throw new ApiException(ApiResponseCode.NULL_POINT);
        });

        return TokenResponse.builder()
                .accessToken(accessToken.getToken())
                .accessTokenExpiredAt(accessToken.getExpiredAt())
                .refreshToken(refreshToken.getToken())
                .refreshTokenExpiredAt(refreshToken.getExpiredAt())
                .build();
    }
}
