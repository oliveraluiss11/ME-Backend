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
        String name = TWILIO_ACCOUNT_SID;
        String property = environment.getProperty(name);
        String message = StringUtil.buildConstantMessageFromText(name, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(property)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getAuthToken() {
        String name = TWILIO_AUTH_TOKEN;
        String property = environment.getProperty(name);
        String message = StringUtil.buildConstantMessageFromText(name, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(property)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getFromNumber() {
        String name = TWILIO_FROM_NUMBER;
        String property = environment.getProperty(name);
        String message = StringUtil.buildConstantMessageFromText(name, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(property)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getReniecToken() {
        String name = RENIEC_TOKEN;
        String property = environment.getProperty(name);
        String message = StringUtil.buildConstantMessageFromText(name, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(property)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getReniecUrl() {
        String name = RENIEC_URL;
        String property = environment.getProperty(name);
        String message = StringUtil.buildConstantMessageFromText(name, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(property)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }

    public String getAllowedOrigin() {
        String name = CORS_ALLOWED_ORIGIN;
        String property = environment.getProperty(name);
        String message = StringUtil.buildConstantMessageFromText(name, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(property)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }
    public String getFirebaseBucketName() {
        String name = FIREBASE_BUCKET_NAME;
        String property = environment.getProperty(name);
        String message = StringUtil.buildConstantMessageFromText(name, NOT_FOUND_MESSAGE);
        return Optional.ofNullable(property)
                .orElseThrow(() -> GenericClientException.create(message, HttpStatus.NOT_FOUND));
    }
}
