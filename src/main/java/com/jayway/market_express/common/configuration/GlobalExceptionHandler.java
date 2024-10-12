package com.jayway.market_express.common.configuration;

import com.jayway.market_express.common.exception.GenericClientException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MissingServletRequestPartException.class)
    public ResponseEntity<ErrorDTO> handleMissingParams(
            MissingServletRequestPartException exception,
            HttpServletRequest request) {
        ErrorDTO errorResponse = ErrorDTO.from(exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorDTO> handleMissingParams(
            MissingServletRequestParameterException exception,
            HttpServletRequest request) {
        ErrorDTO errorResponse = ErrorDTO.from(exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorDTO> handleMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpServletRequest request) {
        ErrorDTO errorResponse = ErrorDTO.from(exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GenericClientException.class)
    public ResponseEntity<ErrorDTO> handleGenericClientException(
            GenericClientException exception,
            HttpServletRequest request) {
        ErrorDTO errorResponse = ErrorDTO.from(exception, request);
        return new ResponseEntity<>(errorResponse, exception.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleException(Exception exception, HttpServletRequest request) {
        ErrorDTO errorResponse = ErrorDTO.from(exception, request);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
