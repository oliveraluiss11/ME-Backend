package com.jayway.market_express.user.repository;

import com.jayway.market_express.user.document.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByCellphoneAndStatus(String cellphone, String status);

    Optional<UserEntity> findByDocumentNumberAndStatus(String documentNumber, String status);
}
