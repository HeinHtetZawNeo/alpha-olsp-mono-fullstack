package com.alpha.olsp.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record UserLoginResponseDto(
        String userId,
        String email,
        String password,
        String firstName,
        String lastName,
        String role,
        String token,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean isAccountNonExpired,
        Boolean isAccountNonLocked,
        Boolean isCredentialsNonExpired,
        Boolean isEnabled
) {
}
