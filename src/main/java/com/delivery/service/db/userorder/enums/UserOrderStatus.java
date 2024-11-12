package com.delivery.service.db.userorder.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UserOrderStatus {
    REGISTERED("등록"),
    UNREGISTERED("해지")
    ;

    private String description;
}
