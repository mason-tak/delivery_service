package com.delivery.service.api.domain.store.controller;

import com.delivery.service.api.common.api.Api;
import com.delivery.service.api.domain.store.business.StoreBusiness;
import com.delivery.service.api.domain.store.controller.model.StoreResponse;
import com.delivery.service.db.store.enums.StoreCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreApiController {
    private final StoreBusiness storeBusiness;

    @GetMapping("search")
    public Api<List<StoreResponse>> search(
            @RequestParam(required = false) StoreCategory storeCategory
    ) {
        List<StoreResponse> response = storeBusiness.searchCategory(storeCategory);

        return Api.OK(response);
    }
}
