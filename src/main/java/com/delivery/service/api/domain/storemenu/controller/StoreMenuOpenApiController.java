package com.delivery.service.api.domain.storemenu.controller;

import com.delivery.service.api.common.api.Api;
import com.delivery.service.api.domain.storemenu.business.StoreMenuBusiness;
import com.delivery.service.api.domain.storemenu.controller.model.StoreMenuRegisterRequest;
import com.delivery.service.api.domain.storemenu.controller.model.StoreMenuResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/open-api/store-menu")
public class StoreMenuOpenApiController {
    private final StoreMenuBusiness storeMenuBusiness;

    public StoreMenuOpenApiController(StoreMenuBusiness storeMenuBusiness) {
        this.storeMenuBusiness = storeMenuBusiness;
    }

    @PostMapping("/register")
    public Api<StoreMenuResponse> register(
            @Valid
            @RequestBody Api<StoreMenuRegisterRequest> request
    ) {
        StoreMenuRegisterRequest req = request.getBody();
        StoreMenuResponse response = storeMenuBusiness.register(req);
        return Api.OK(response);
    }
}
