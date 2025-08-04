package com.openfeign.client.config;

import com.openfeign.client.interceptor.MovieRequestInterceptor;
import com.openfeign.service.DummyAuthTokenService;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MovieFeignClientConfig {

    @Bean
    public RequestInterceptor apiKeyRequestInterceptor(DummyAuthTokenService dummyAuthTokenService) {
        return new MovieRequestInterceptor(dummyAuthTokenService);
    }
}
