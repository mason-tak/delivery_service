package com.delivery.service.api.domain.userordermenu.converter;

import com.delivery.service.api.common.annotation.Converter;
import com.delivery.service.db.storemenu.StoreMenuEntity;
import com.delivery.service.db.userorder.UserOrderEntity;
import com.delivery.service.db.userordermenu.UserOrderMenuEntity;

@Converter
public class UserOrderMenuConverter {
    public UserOrderMenuEntity toEntity(UserOrderEntity userOrderEntity, StoreMenuEntity storeMenuEntity) {
        return UserOrderMenuEntity.builder()
                .userOrderId(userOrderEntity.getId())
                .storeMenuId(storeMenuEntity.getId())
                .build();
    }
}
