package com.delivery.service.api.domain.store.controller.model;

import com.delivery.service.db.store.enums.StoreCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreRegisterRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotNull(message = "store category must not be null")  // enum 타입이기때문에 null 값 들어오면 안됨
    private StoreCategory storeCategory;

    @NotBlank
    private String thumbnailUrl;

    @NotNull(message = "minimum amount must not be null")
    private BigDecimal minimumAmount;

    @NotNull(message = "minimum delivery amount must not be null")
    private BigDecimal minimumDeliveryAmount;

    @NotBlank
    private String phoneNumber;
}
