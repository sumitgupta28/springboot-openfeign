package com.openfeign.service;

import com.openfeign.client.MovieApiFeignClient;
import com.openfeign.entity.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {
    private final MovieApiFeignClient movieApiClient;
    public Movie getMovieDetailsWithCountBasedCircuitBreaker(String movieId) {
        return movieApiClient.getMovieDetails(movieId);
    }
}