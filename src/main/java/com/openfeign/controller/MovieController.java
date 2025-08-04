package com.openfeign.controller;

import com.openfeign.entity.Movie;
import com.openfeign.service.MovieService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequestMapping("/movies")
@Slf4j
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        MDC.put("requestId",UUID.randomUUID().toString());
        Movie movie = movieService.getMovieDetailsWithCountBasedCircuitBreaker(id);
        MDC.clear();
        return  ResponseEntity.ok(movie);
    }
}
