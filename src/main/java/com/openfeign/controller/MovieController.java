package com.openfeign.controller;

import com.openfeign.entity.ErrorResponse;
import com.openfeign.entity.Movie;
import com.openfeign.exception.MovieBadRequestException;
import com.openfeign.exception.MovieInternalServerException;
import com.openfeign.exception.MovieNotFoundException;
import com.openfeign.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequestMapping("/movies")
@Slf4j
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @ExceptionHandler({MovieBadRequestException.class, MovieNotFoundException.class, MovieInternalServerException.class})
    public ResponseEntity<ErrorResponse> handleMovieNotFoundException(MovieNotFoundException ex) {
        log.error(" Message  {} , httpStatusCode {} ",ex.getMessage(),ex.getErrorCode());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), ex.getErrorCode());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        MDC.put("requestId",UUID.randomUUID().toString());
        Movie movie = movieService.getMovieDetailsWithCountBasedCircuitBreaker(id);
        MDC.clear();
        return  ResponseEntity.ok(movie);
    }
}
