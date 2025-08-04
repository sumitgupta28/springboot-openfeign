package com.openfeign.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class MovieBadRequestException extends RuntimeException {

    final HttpStatus errorCode;
    final String errorMessage;

    public MovieBadRequestException(HttpStatus errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

}
