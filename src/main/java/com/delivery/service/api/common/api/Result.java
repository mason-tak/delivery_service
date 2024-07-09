package com.delivery.service.api.common.api;

import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.api.common.response.ResponseCodeIfs;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Result {
    private Integer resultCode;
    private String resultMessage;
    private String resultDescription;

    public static Result OK() {
        return Result.builder()
                .resultCode(ApiResponseCode.OK.getApplicationStatusCode())
                .resultMessage(ApiResponseCode.OK.getDescription())
                .resultDescription("성공")
                .build();
    }

    public static Result ERROR(ResponseCodeIfs responseCodeIfs) {
        return Result.builder()
                .resultCode(responseCodeIfs.getApplicationStatusCode())
                .resultMessage(responseCodeIfs.getDescription())
                .resultDescription("실패 - 에러")
                .build();
    }

    public static Result ERROR(ResponseCodeIfs responseCodeIfs, String description) {
        return Result.builder()
                .resultCode(responseCodeIfs.getApplicationStatusCode())
                .resultMessage(responseCodeIfs.getDescription())
                .resultDescription(description)
                .build();
    }
}
