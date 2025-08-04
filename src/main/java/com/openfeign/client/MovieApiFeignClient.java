package com.openfeign.client;

import com.openfeign.entity.Movie;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "mock-movie-service", url = "http://localhost:9090") // 'my-service' is the service name, 'url' is the base URL
public interface MovieApiFeignClient {

    @GetMapping("/mock/movies/{id}")
    Movie getMovieDetails(@PathVariable("id") String movieId);

}