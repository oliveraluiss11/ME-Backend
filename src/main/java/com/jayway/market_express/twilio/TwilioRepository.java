package com.jayway.market_express.twilio;

public interface TwilioRepository {
    void sendSms(String to, String body);
}
