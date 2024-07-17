package com.delivery.service.api.domain.token.service;

import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.api.domain.token.ifs.TokenHelpersIfs;
import com.delivery.service.api.domain.token.model.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenHelpersIfs tokenHelpersIfs;

    public TokenDto issueAccessToken(Long userId) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", userId);

        return tokenHelpersIfs.accessToken(data);
    }

    public TokenDto issueRefreshToken(Long userId) {
        HashMap<String, Object> data = new HashMap<>();
        data.put("userId", userId);

        return tokenHelpersIfs.refreshToken(data);
    }

    public Long validationToken(String token) {
        Map<String, Object> map = tokenHelpersIfs.validationTokenWithThrow(token);
        Object userId = map.get("userId");

        Objects.requireNonNull(userId, () -> {
            throw new ApiException(ApiResponseCode.NULL_POINT);
        });

        return Long.parseLong(userId.toString());
    }
}
