package com.delivery.service.api.exceptionhandler;

import com.delivery.service.api.common.api.Api;
import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ResponseCodeIfs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(value = Integer.MIN_VALUE)
public class ApiExceptionHandler {
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Api<Object>> apiException(ApiException apiException) {
        log.error("", apiException);

        ResponseCodeIfs responseCodeIfs = apiException.getResponseCodeIfs();

        return ResponseEntity
                .status(responseCodeIfs.getHttpStatusCode())
                .body(Api.ERROR(responseCodeIfs, apiException.getErrorDescription()));
    }
}
