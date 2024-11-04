package com.jayway.market_express.store.service;

import com.jayway.market_express.store.dto.CreateStoreRequest;

public interface StoreService {
    void registerStore(CreateStoreRequest request);
}
