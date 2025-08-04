package com.openfeign.service;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DummyAuthTokenService {

    public String getAuthToken()
    {
        return UUID.randomUUID().toString();
    }
}
