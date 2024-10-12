package com.jayway.market_express.common.configuration;

import com.jayway.market_express.common.exception.GenericClientException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.time.LocalDateTime;

import static com.jayway.market_express.common.constant.MessageConstant.MISSING_PARAMETER_MESSAGE;

@AllArgsConstructor
@Getter
public class ErrorDTO {
    private String code;
    private String message;
    private HttpStatus httpStatus;
    private LocalDateTime registrationDate;
    private String path;

    public static ErrorDTO from(GenericClientException exception, HttpServletRequest request) {
        return new ErrorDTO(exception.getCode(), exception.getMessage(), exception.getHttpStatus(), exception.getRegistrationDate(), request.getRequestURI());
    }

    public static ErrorDTO from(Exception exception, HttpServletRequest request) {
        return new ErrorDTO(null, exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, LocalDateTime.now(), request.getRequestURI());
    }

    public static ErrorDTO from(MissingServletRequestParameterException exception, HttpServletRequest request) {
        String message = String.format(MISSING_PARAMETER_MESSAGE, exception.getParameterName(), exception.getParameterType());
        return new ErrorDTO(null, message, HttpStatus.BAD_REQUEST, LocalDateTime.now(), request.getRequestURI());
    }

    public static ErrorDTO from(HttpMessageNotReadableException exception, HttpServletRequest request) {
        String exceptionMessage = exception.getMessage().split(":")[0];
        return new ErrorDTO(null, exceptionMessage, HttpStatus.BAD_REQUEST, LocalDateTime.now(), request.getRequestURI());
    }

    public static ErrorDTO from(MissingServletRequestPartException exception, HttpServletRequest request) {
        return new ErrorDTO(null, exception.getMessage(), HttpStatus.BAD_REQUEST, LocalDateTime.now(), request.getRequestURI());
    }
}
