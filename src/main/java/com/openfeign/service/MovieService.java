package com.openfeign.service;

import com.openfeign.client.MovieApiFeignClient;
import com.openfeign.entity.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;


@Service
@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieApiFeignClient movieApiClient;

    public Movie getMovieDetailsWithCountBasedCircuitBreaker(String movieId) {
        return fetchMovieDetails(movieId);
    }

    public Movie movieDetailsFallbackMethod(String movieId, Exception exception) {
        log.info("Fallback method called.");
        log.info("CallNotPermittedException exception message: {}", exception.getMessage());
        return new Movie("Default", "N/A", "N/A", 0.0);
    }

    private Movie fetchMovieDetails(String movieId) {
        Movie movie = null;
        try {
            movie = movieApiClient.getMovieDetails(movieId);
        } catch (HttpServerErrorException httpServerErrorException) {
            log.error("Received HTTP server error exception while fetching the movie details. Error Message: {}", httpServerErrorException.getMessage());
            throw httpServerErrorException;
        } catch (HttpClientErrorException httpClientErrorException) {
            log.error("Received HTTP client error exception while fetching the movie details. Error Message: {}", httpClientErrorException.getMessage());
            throw httpClientErrorException;
        } catch (ResourceAccessException resourceAccessException) {
            log.error("Received Resource Access exception while fetching the movie details.");
            throw resourceAccessException;
        } catch (Exception exception) {
            log.error("Unexpected error encountered while fetching the movie details");
            throw exception;
        }
        return movie;
    }




}