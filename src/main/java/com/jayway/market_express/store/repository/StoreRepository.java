package com.jayway.market_express.store.repository;

import com.jayway.market_express.store.entity.StoreEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StoreRepository extends MongoRepository<StoreEntity, String> {
}
