package com.alpha.olsp.dto.request;

public record AuthenticationRequestDto(
        String username,
        String password
) {
}
