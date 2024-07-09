package com.delivery.service.api.common.api;

import com.delivery.service.api.common.response.ResponseCodeIfs;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Api<T> {

    private Result result;

    @Valid
    private T body;

    public static <T> Api<T> OK(T data) {
        Api<T> api = new Api<>();
        api.result = Result.OK();
        api.body = data;

        return api;
    }

    public static Api<Object> ERROR(Result result) {
        Api<Object> api = new Api<>();
        api.result = result;

        return api;
    }

    public static Api<Object> ERROR(ResponseCodeIfs responseCodeIfs) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(responseCodeIfs);

        return api;
    }

    public static Api<Object> ERROR(ResponseCodeIfs responseCodeIfs, String description) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(responseCodeIfs, description);

        return api;
    }
}
