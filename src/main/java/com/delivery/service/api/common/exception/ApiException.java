package com.delivery.service.api.common.exception;

import com.delivery.service.api.common.response.ResponseCodeIfs;
import lombok.Getter;

@Getter
public class ApiException extends RuntimeException implements ApiExceptionIfs {
    private final ResponseCodeIfs responseCodeIfs;

    private final String errorDescription;

    public ApiException(ResponseCodeIfs responseCodeIfs) {
        super(responseCodeIfs.getDescription());
        this.responseCodeIfs = responseCodeIfs;
        this.errorDescription = responseCodeIfs.getDescription();
    }

    public ApiException(ResponseCodeIfs responseCodeIfs, String errorDescription) {
        super(errorDescription);
        this.responseCodeIfs = responseCodeIfs;
        this.errorDescription = errorDescription;
    }

}
