package com.delivery.service.api.domain.userorder.controller.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderRequest {
    @NotNull
    private Long storeId;

    // 주문
    // 특정 사용자가 특정 메뉴를 주문 (특정 사용자 - 로그인된 사용자)
    @NotNull
    private List<Long> storeMenuIdList;
}
