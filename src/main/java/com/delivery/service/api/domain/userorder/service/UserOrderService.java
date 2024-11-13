package com.delivery.service.api.domain.userorder.service;

import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.db.userorder.UserOrderEntity;
import com.delivery.service.db.userorder.UserOrderRepository;
import com.delivery.service.db.userorder.enums.UserOrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserOrderService {
    private final UserOrderRepository userOrderRepository;

    public UserOrderEntity getUserOrderWithThrow(Long id, Long userId) {
        return userOrderRepository.findAllByIdAndStatusAndUserId(id, UserOrderStatus.REGISTERED, userId)
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }

    public List<UserOrderEntity> getUserOrderList(Long userId) {
        return userOrderRepository.findAllByUserIdAndStatusOrderByIdDesc(userId, UserOrderStatus.REGISTERED);
    }

    public List<UserOrderEntity> getUserOrderList(Long userId, List<UserOrderStatus> statusList) {
        return userOrderRepository.findAllByUserIdAndStatusInOrderByIdDesc(userId, statusList);
    }

    // 현재 진행중인 내역
    public List<UserOrderEntity> current(Long userId) {
        return getUserOrderList(
                userId,
                List.of(
                        UserOrderStatus.ORDER,
                        UserOrderStatus.COOKING,
                        UserOrderStatus.DELIVERY,
                        UserOrderStatus.ACCEPT
                )
        );
    }

    // 과거 주문한 내역
    public List<UserOrderEntity> history(Long userId) {
        return getUserOrderList(
                userId,
                List.of(
                        UserOrderStatus.RECEIVE
                )
        );
    }

    // 주문 (create)
    public UserOrderEntity order(UserOrderEntity userOrderEntity) {
        return Optional.ofNullable(userOrderEntity)
                .map(it -> {
                    it.setStatus(UserOrderStatus.ORDER);
                    it.setOrderedAt(LocalDateTime.now());
                    return userOrderRepository.save(it);
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT));
    }

    // 상태 변경
    public UserOrderEntity setStatus(UserOrderEntity userOrderEntity, UserOrderStatus status) {
        userOrderEntity.setStatus(status);

        return userOrderRepository.save(userOrderEntity);
    }

    // 주문 확인
    public UserOrderEntity accept(UserOrderEntity userOrderEntity) {
        userOrderEntity.setAcceptedAt(LocalDateTime.now());
        return setStatus(userOrderEntity, UserOrderStatus.ACCEPT);
    }

    // 조리 시작
    public UserOrderEntity cooking(UserOrderEntity userOrderEntity) {
        userOrderEntity.setCookingStartedAt(LocalDateTime.now());
        return setStatus(userOrderEntity, UserOrderStatus.COOKING);
    }

    // 배달 시작
    public UserOrderEntity delivery(UserOrderEntity userOrderEntity) {
        userOrderEntity.setDeliveryStartedAt(LocalDateTime.now());
        return setStatus(userOrderEntity, UserOrderStatus.DELIVERY);
    }

    // 배달 완료
    public UserOrderEntity receive(UserOrderEntity userOrderEntity) {
        userOrderEntity.setReceivedAt(LocalDateTime.now());
        return setStatus(userOrderEntity, UserOrderStatus.RECEIVE);
    }
}
