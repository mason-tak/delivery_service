package com.delivery.service.api.domain.userordermenu.service;

import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.db.userordermenu.UserOrderMenuEntity;
import com.delivery.service.db.userordermenu.UserOrderMenuRepository;
import com.delivery.service.db.userordermenu.enums.UserOrderMenuStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderMenuService {
    private final UserOrderMenuRepository userOrderMenuRepository;

    public List<UserOrderMenuEntity> getUserOrderMenu(Long userOrderId) {
        return userOrderMenuRepository.findAllByUserOrderIdAndStatus(userOrderId, UserOrderMenuStatus.REGISTERED);
    }

    public UserOrderMenuEntity order(UserOrderMenuEntity userOrderMenuEntity) {
        return Optional.ofNullable(userOrderMenuEntity)
                .map(it -> {
                    it.setStatus(UserOrderMenuStatus.REGISTERED);
                    return userOrderMenuRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }
}
