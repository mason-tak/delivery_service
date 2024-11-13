package com.delivery.service.api.domain.userorder.controller;

import com.delivery.service.api.common.annotation.UserSession;
import com.delivery.service.api.common.api.Api;
import com.delivery.service.api.domain.user.model.User;
import com.delivery.service.api.domain.userorder.business.UserOrderBusiness;
import com.delivery.service.api.domain.userorder.controller.model.UserOrderRequest;
import com.delivery.service.api.domain.userorder.controller.model.UserOrderResponse;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user-order")
public class UserOrderApiController {
    private final UserOrderBusiness userOrderBusiness;

    // 사용자 주문
    @PostMapping("")
    public Api<UserOrderResponse> userOrder(
            @Valid @RequestBody Api<UserOrderRequest> userOrderRequest,
            @Parameter(hidden = true) @UserSession User user
    ) {
        var response = userOrderBusiness.userOrder(
                user,
                userOrderRequest.getBody()
        );
        return Api.OK(response);
    }
}
