package com.openfeign.client.interceptor;

import com.openfeign.service.DummyAuthTokenService;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Component
@RequiredArgsConstructor
@Slf4j
public class MovieRequestInterceptor implements RequestInterceptor {

    final DummyAuthTokenService dummyAuthTokenService;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("authToken", dummyAuthTokenService.getAuthToken());
        log.info("RequestTemplate headers {}",requestTemplate.headers());
        log.info("RequestTemplate feignTarget name {}",requestTemplate.feignTarget().name());
        log.info("RequestTemplate url {}",requestTemplate.feignTarget().url());

    }
}
