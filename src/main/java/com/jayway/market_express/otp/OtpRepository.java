package com.jayway.market_express.otp;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpRepository extends MongoRepository<OtpEntity, String> {
    Optional<OtpEntity> findByCellphoneAndCodeAndStatus(String cellphone, String code, String status);

    Optional<OtpEntity> findByCellphoneAndStatus(String cellphone, String status);

}
