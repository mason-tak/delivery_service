package com.delivery.service.api.domain.user.service;

import com.delivery.service.api.common.exception.ApiException;
import com.delivery.service.api.common.response.ApiResponseCode;
import com.delivery.service.db.user.UserEntity;
import com.delivery.service.db.user.UserRepository;
import com.delivery.service.db.user.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

// user 도메인 로직 처리
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserEntity register(UserEntity userEntity) {
        return Optional.ofNullable(userEntity)
                .map(it -> {
                    userEntity.setStatus(UserStatus.REGISTERED);
                    userEntity.setRegisteredAt(LocalDateTime.now());
                    return userRepository.save(userEntity);
                })
                .orElseThrow(() -> new ApiException(ApiResponseCode.NULL_POINT, "User Service register Null Point"));

    }
}
