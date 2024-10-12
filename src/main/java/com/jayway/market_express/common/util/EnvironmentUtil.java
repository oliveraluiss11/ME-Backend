package com.jayway.market_express.common.util;

import com.jayway.market_express.common.exception.GenericClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.jayway.market_express.common.constant.EnvironmentConstant.*;
import static com.jayway.market_express.common.constant.MessageConstant.NOT_FOUND_MESSAGE;

@Component
@RequiredArgsConstructor
public class EnvironmentUtil {
    private final Environment environment;

    public String getAccountSid() {
        String accountSid = environment.getProperty(TWILIO_ACCOUNT_SID);
        String message = StringUtil.buildConstantMessageFromText(TWILIO_ACCOUNT_SID, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(accountSid)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getAuthToken() {
        String accountSid = environment.getProperty(TWILIO_AUTH_TOKEN);
        String message = StringUtil.buildConstantMessageFromText(TWILIO_ACCOUNT_SID, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(accountSid)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getFromNumber() {
        String accountSid = environment.getProperty(TWILIO_FROM_NUMBER);
        String message = StringUtil.buildConstantMessageFromText(TWILIO_ACCOUNT_SID, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(accountSid)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getReniecToken() {
        String accountSid = environment.getProperty(RENIEC_TOKEN);
        String message = StringUtil.buildConstantMessageFromText(RENIEC_TOKEN, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(accountSid)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getReniecUrl() {
        String accountSid = environment.getProperty(RENIEC_URL);
        String message = StringUtil.buildConstantMessageFromText(RENIEC_URL, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(accountSid)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getAllowedOrigin() {
        String accountSid = environment.getProperty(CORS_ALLOWED_ORIGIN);
        String message = StringUtil.buildConstantMessageFromText(RENIEC_URL, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(accountSid)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }
}
