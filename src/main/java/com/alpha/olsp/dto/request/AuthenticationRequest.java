package com.alpha.olsp.dto.request;

public record AuthenticationRequest(
        String username,
        String password
) {
}
