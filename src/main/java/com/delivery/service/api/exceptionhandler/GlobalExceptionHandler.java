package com.delivery.service.api.exceptionhandler;

import com.delivery.service.api.common.api.Api;
import com.delivery.service.api.common.response.ApiResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MAX_VALUE)
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> exception(Exception exception) {
        log.error("api error : ", exception);

        return ResponseEntity
                .status(500)
                .body(Api.ERROR(ApiResponseCode.SERVER_ERROR));
    }
}
