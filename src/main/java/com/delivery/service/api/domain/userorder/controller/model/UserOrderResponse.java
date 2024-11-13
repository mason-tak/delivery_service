package com.delivery.service.api.domain.userorder.controller.model;

import com.delivery.service.db.userorder.enums.UserOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOrderResponse {
    private Long id;

    private UserOrderStatus status;

    private LocalDateTime orderedAt;

    private BigDecimal amount;

    private LocalDateTime acceptedAt;

    private LocalDateTime cookingStartedAt;

    private LocalDateTime deliveryStartedAt;

    private LocalDateTime receivedAt;
}
