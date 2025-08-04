package com.openfeign.client;

import com.openfeign.entity.Movie;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mock-movie-service")
public interface MovieApiFeignClient {
    Logger log = LoggerFactory.getLogger(MovieApiFeignClient.class); // Manual logger


    @Retry(name = "movieServiceRetryOnException",fallbackMethod = "fallbackDefaultMovieDetails")
    @GetMapping("/mock/movies/{id}")
    Movie getMovieDetails(@PathVariable("id") String movieId);

    default Movie fallbackDefaultMovieDetails(Throwable t) {
        log.info("fallbackDefaultMovieDetails .....");
        return new Movie("Default", "N/A", "N/A", 0.0);
    }

}