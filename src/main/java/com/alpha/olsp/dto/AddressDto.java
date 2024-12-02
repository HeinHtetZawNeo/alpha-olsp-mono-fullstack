package com.alpha.olsp.dto;

import lombok.Builder;

@Builder
public record AddressDto(
        String addressLine1,
        String addressLine2,
        String city,
        StateDto state,
        String postalCode
) {
}