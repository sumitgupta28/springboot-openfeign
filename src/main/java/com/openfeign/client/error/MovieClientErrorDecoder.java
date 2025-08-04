package com.openfeign.client.error;

import com.openfeign.exception.MovieBadRequestException;
import com.openfeign.exception.MovieInternalServerException;
import com.openfeign.exception.MovieNotFoundException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Slf4j
public class MovieClientErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        log.error(" methodKey {} ",methodKey);
        log.error(" status {} ",response.status());
        log.error(" headers {} ",response.headers());


        return switch (response.status()) {
            case 400 -> new MovieBadRequestException(HttpStatus.BAD_REQUEST, "BAD_REQUEST");
            case 404 -> new MovieNotFoundException(HttpStatus.NOT_FOUND, "NOT_FOUND");
            case 500 -> new MovieInternalServerException(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL_SERVER_ERROR");
            default -> new Exception("Generic error");
        };
    }
}
