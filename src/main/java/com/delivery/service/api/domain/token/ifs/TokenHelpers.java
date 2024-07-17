package com.delivery.service.api.domain.token.ifs;

import com.delivery.service.api.domain.token.model.TokenDto;

import java.util.Map;

public interface TokenHelpers {
    TokenDto accessToken(Map<String, Object> data);

    TokenDto refreshToken(Map<String, Object> data);

    Map<String, Object> validationTokenWithThrow(String data);
}
