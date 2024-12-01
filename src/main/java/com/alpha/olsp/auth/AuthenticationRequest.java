package com.alpha.olsp.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}
