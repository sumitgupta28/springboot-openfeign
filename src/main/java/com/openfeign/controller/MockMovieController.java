package com.openfeign.controller;

import com.openfeign.entity.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/mock/movies")
@Slf4j
public class MockMovieController {

    @GetMapping("/{id}")
    public ResponseEntity<?> getMovieById(@PathVariable String id,@RequestHeader Map<String, String> headers) throws InterruptedException {
        log.debug("headers - {} ",headers);
        Movie movie = null;
        switch (id) {
            case "1" -> {
                    movie = new Movie("1", "The Matrix", "Lana Wachowski, Lilly Wachowski", 8.7);
            } // end of 1
            case "2" -> {
                Thread.sleep(2000);
                movie = new Movie("2", "Inception", "Christopher Nolan", 8.8);
            } // end of 2
            case "3" -> {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("NOT_FOUND");
            } // end of 3
            case "4" -> {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("BAD_REQUEST");
            } // end of 4
            case "5" -> {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("INTERNAL_SERVER_ERROR");
            } // end of 5
        }
        return ResponseEntity.ok(movie);
    }
}
