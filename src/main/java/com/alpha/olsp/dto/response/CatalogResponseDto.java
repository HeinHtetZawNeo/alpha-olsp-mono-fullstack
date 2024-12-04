package com.alpha.olsp.dto.response;

import java.util.List;

public record CatalogResponseDto(
        String id,
        String name,
        String description,
        List<SubCatalogResponseDto> subCatalogs
) {
}
