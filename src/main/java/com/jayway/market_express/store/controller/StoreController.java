package com.jayway.market_express.store.controller;

import com.jayway.market_express.store.dto.CreateStoreRequest;
import com.jayway.market_express.store.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.jayway.market_express.common.constant.PathConstant.STORE_PATH;

@RestController
@RequestMapping(STORE_PATH)
@RequiredArgsConstructor
public class StoreController {
    private final StoreService storeService;
    @PostMapping
    public ResponseEntity<Void> registerStore(@RequestBody CreateStoreRequest request){
        storeService.registerStore(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
