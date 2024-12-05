package com.alpha.olsp.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CatalogRequestDto(
        @NotBlank
        String name,
        String description,
        String parentCatalogId
) {
}
