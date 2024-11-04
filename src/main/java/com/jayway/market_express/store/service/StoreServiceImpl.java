package com.jayway.market_express.store.service;

import com.jayway.market_express.store.dto.CreateStoreRequest;
import com.jayway.market_express.store.entity.StoreEntity;
import com.jayway.market_express.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService{
    private final StoreRepository storeRepository;
    @Override
    public void registerStore(CreateStoreRequest request) {
        StoreEntity storeEntity = StoreEntity.from(request);
        storeRepository.save(storeEntity);
    }
}
