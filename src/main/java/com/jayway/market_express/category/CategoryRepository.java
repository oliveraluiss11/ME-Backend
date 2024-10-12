package com.jayway.market_express.category;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<CategoryEntity, String> {
}
