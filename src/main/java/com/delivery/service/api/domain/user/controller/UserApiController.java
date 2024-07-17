package com.delivery.service.api.domain.user.controller;

import com.delivery.service.api.common.annotation.UserSession;
import com.delivery.service.api.common.api.Api;
import com.delivery.service.api.domain.user.business.UserBusiness;
import com.delivery.service.api.domain.user.controller.model.UserResponse;
import com.delivery.service.api.domain.user.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserApiController {
    private final UserBusiness userBusiness;

    @GetMapping("/me")
    public Api<UserResponse> me(@UserSession User user) {
        UserResponse response = userBusiness.me(user);
        return Api.OK(response);
    }
}
