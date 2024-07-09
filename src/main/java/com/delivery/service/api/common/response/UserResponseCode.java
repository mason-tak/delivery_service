package com.delivery.service.api.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum UserResponseCode implements ResponseCodeIfs {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 1404, "사용자를 찾을 수 없습니다.");

    private final Integer httpStatusCode;
    private final Integer ApplicationStatusCode;
    private final String description;
}
