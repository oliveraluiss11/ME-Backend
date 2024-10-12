package com.jayway.market_express.common.exception;

import com.jayway.market_express.common.util.DateUtil;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class GenericClientException extends RuntimeException {
    private String code;
    private final HttpStatus httpStatus;
    private final LocalDateTime registrationDate;

    public GenericClientException(String code, String message, HttpStatus httpStatus, LocalDateTime registrationDate) {
        super(message);
        this.httpStatus = httpStatus;
        this.registrationDate = registrationDate;
        this.code = code;
    }

    public GenericClientException(String message, HttpStatus httpStatus, LocalDateTime registrationDate) {
        super(message);
        this.httpStatus = httpStatus;
        this.registrationDate = registrationDate;
    }

    public static GenericClientException create(String message, HttpStatus httpStatus) {
        return new GenericClientException(message, httpStatus, DateUtil.getLocalDateTime());
    }

    public static GenericClientException create(String code, String message, HttpStatus httpStatus) {
        return new GenericClientException(code, message, httpStatus, DateUtil.getLocalDateTime());
    }
}
