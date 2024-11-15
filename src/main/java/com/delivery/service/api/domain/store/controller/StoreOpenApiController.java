package com.delivery.service.api.domain.store.controller;

import com.delivery.service.api.common.api.Api;
import com.delivery.service.api.domain.store.business.StoreBusiness;
import com.delivery.service.api.domain.store.controller.model.StoreRegisterRequest;
import com.delivery.service.api.domain.store.controller.model.StoreResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api/store")
@RequiredArgsConstructor
@Slf4j
public class StoreOpenApiController {
    private final StoreBusiness storeBusiness;

    @PostMapping("/register")
    public Api<StoreResponse> register(
            @Valid @RequestBody Api<StoreRegisterRequest> request
    ) {
        StoreResponse response = storeBusiness.register(request.getBody());

        return Api.OK(response);
    }
}
