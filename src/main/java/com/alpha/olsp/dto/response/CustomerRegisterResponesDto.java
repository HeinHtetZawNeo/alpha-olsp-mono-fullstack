package com.alpha.olsp.dto.response;

import com.alpha.olsp.dto.AddressDto;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CustomerRegisterResponesDto(
        String userID,
        String email,
        String firstName,
        String lastName,
        AddressDto address,
        String role,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Boolean isAccountNonExpired,
        Boolean isAccountNonLocked,
        Boolean isCredentialsNonExpired,
        Boolean isEnabled
) {
}
