package com.delivery.service.api.common.exception;

import com.delivery.service.api.common.response.ResponseCodeIfs;

public interface ApiExceptionIfs {
    ResponseCodeIfs getResponseCodeIfs();

    String getErrorDescription();
}
