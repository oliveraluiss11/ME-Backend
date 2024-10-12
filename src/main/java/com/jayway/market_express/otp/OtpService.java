package com.jayway.market_express.otp;


public interface OtpService {
    void generateOtp(String cellphone);

    void ensureValidOtp(String cellphone, String otp);
}
