package com.jayway.market_express.rider_status_history;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RiderStatusHistoryRepository extends MongoRepository<RiderStatusHistoryEntity, String> {
}
