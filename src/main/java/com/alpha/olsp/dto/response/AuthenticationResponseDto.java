package com.alpha.olsp.dto.response;

public record AuthenticationResponseDto(
        String token,
        String email,
        String role
) {
}
