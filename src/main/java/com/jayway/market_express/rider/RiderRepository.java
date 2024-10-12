package com.jayway.market_express.rider;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RiderRepository extends MongoRepository<RiderEntity, String> {
}
