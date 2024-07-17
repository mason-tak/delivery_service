package com.delivery.service.api.domain.token.helper;

import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.TokenResponseCode;
import com.delivery.service.api.domain.token.ifs.TokenHelpers;
import com.delivery.service.api.domain.token.model.TokenDto;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtTokenHelper implements TokenHelpers {
    @Value("${token.secret.key}")
    private String secretKey;

    @Value("${token.access-key.plus-hour}")
    private Long accessTokenPlusHour;

    @Value("${token.refresh-key.plus-hour}")
    private Long refreshTokenPlusHour;

    @Override
    public TokenDto accessToken(Map<String, Object> data) {
        return getJwtToken(data, accessTokenPlusHour);
    }

    @Override
    public TokenDto refreshToken(Map<String, Object> data) {
        return getJwtToken(data, refreshTokenPlusHour);
    }

    private TokenDto getJwtToken(Map<String, Object> data, Long TokenPlusHour) {
        LocalDateTime expiredLocalDateTime = LocalDateTime.now().plusHours(TokenPlusHour);
        var expiredAt = Date.from(expiredLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());

        String jwtToken = Jwts.builder().signWith(key, SignatureAlgorithm.HS256)
                .setClaims(data)
                .setExpiration(expiredAt)
                .compact();

        return TokenDto.builder()
                .token(jwtToken)
                .expiredAt(expiredLocalDateTime)
                .build();
    }


    @Override
    public Map<String, Object> validationTokenWithThrow(String token) {
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
        JwtParser parser = Jwts.parserBuilder().setSigningKey(key).build();

        try {
            Jws<Claims> result = parser.parseClaimsJws(token);

            return new HashMap<String, Object>(result.getBody());
        } catch (Exception e) {
            if (e instanceof SignatureException) {
                // 유효하지 않은 토큰
                log.error("token signature exception error : {}", e);
                throw new ApiException(TokenResponseCode.INVALID_TOKEN);
            } else if (e instanceof ExpiredJwtException) {
                // 만료된 토큰
                log.error("token expired error : {}", e);
                throw new ApiException(TokenResponseCode.EXPIRED_TOKEN);
            } else {
                // 그 외 에러
                log.error("token exception error : {}", e);
                throw new ApiException(TokenResponseCode.TOKEN_EXCEPTION);
            }
        }
    }
}
