package com.delivery.service.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum TokenResponseCode implements ResponseCodeIfs {
    INVALID_TOKEN(HttpStatus.BAD_REQUEST.value(), 2001, "유효하지 않은 토큰"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST.value(), 2002, "만료된 토큰"),
    TOKEN_EXCEPTION(HttpStatus.BAD_REQUEST.value(), 2003, "알수없는 토큰 에러");

    private final Integer httpStatusCode;
    private final Integer ApplicationStatusCode;
    private final String description;
}
