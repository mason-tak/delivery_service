package com.delivery.service.api.domain.userorder.business;

import com.delivery.service.api.common.annotation.Business;
import com.delivery.service.api.domain.storemenu.service.StoreMenuService;
import com.delivery.service.api.domain.user.model.User;
import com.delivery.service.api.domain.userorder.controller.model.UserOrderRequest;
import com.delivery.service.api.domain.userorder.controller.model.UserOrderResponse;
import com.delivery.service.api.domain.userorder.converter.UserOrderConverter;
import com.delivery.service.api.domain.userorder.service.UserOrderService;
import com.delivery.service.api.domain.userordermenu.converter.UserOrderMenuConverter;
import com.delivery.service.api.domain.userordermenu.service.UserOrderMenuService;
import com.delivery.service.db.storemenu.StoreMenuEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Business
public class UserOrderBusiness {
    private final UserOrderService userOrderService;
    private final StoreMenuService storeMenuService;
    private final UserOrderConverter userOrderConverter;
    private final UserOrderMenuConverter userOrderMenuConverter;
    private final UserOrderMenuService userOrderMenuService;

    // 1. 사용자, 메뉴 id
    // 2. userOrder 생성
    // 3. userOrderMenu 생성
    // 4. 응답 생성
    public UserOrderResponse userOrder(User user, UserOrderRequest body) {
        var storeMenuEntityList = body.getStoreMenuIdList()
                .stream()
                .map(it -> storeMenuService.getStoreMenuWithThrow(it))
                .collect(Collectors.toList());

        var userOrderEntity = userOrderConverter.toEntity(user, storeMenuEntityList);

        // 주문
        var newUserOrderEntity = userOrderService.order(userOrderEntity);

        // 맵핑
        var userOrderMenuEntityList = storeMenuEntityList.stream()
                .map(it -> {
                    // menu +user order
                    var userOrderMenuEntity = userOrderMenuConverter.toEntity(newUserOrderEntity, it);
                    return userOrderMenuEntity;
                })
                .collect(Collectors.toList());

        // 주문내역 기록 남기기
        userOrderMenuEntityList.forEach(it -> {
            userOrderMenuService.order(it);
        });

        return userOrderConverter.toResponse(newUserOrderEntity);
    }
}
