package com.grepp.global.auth;

import org.springframework.http.ResponseCookie;

import java.util.UUID;

public class CookieProvider {

    public static ResponseCookie createCookie() {
        return ResponseCookie.from("userCart")
                .value(UUID.randomUUID().toString())
                .maxAge(60 * 60)
                .build();
    }

    private CookieProvider() {}
}
