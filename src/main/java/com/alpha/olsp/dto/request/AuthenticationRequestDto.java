package com.alpha.olsp.dto.request;

public record AuthenticationRequestDto(
        String email,
        String password
) {
}
