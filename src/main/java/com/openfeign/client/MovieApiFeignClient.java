package com.openfeign.client;

import com.openfeign.config.FeignClientConfig;
import com.openfeign.entity.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mock-movie-service", url = "${movie-mock-service.endpoint}",configuration = FeignClientConfig.class) // 'my-service' is the service name, 'url' is the base URL
public interface MovieApiFeignClient {

    @GetMapping("/mock/movies/{id}")
    ResponseEntity<Movie> getMovieDetails(@PathVariable("id") String movieId);

}