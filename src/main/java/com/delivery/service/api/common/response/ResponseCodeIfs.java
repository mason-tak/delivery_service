package com.delivery.service.api.common.response;

public interface ResponseCodeIfs {
    Integer getHttpStatusCode();

    Integer getApplicationStatusCode();

    String getDescription();
}
