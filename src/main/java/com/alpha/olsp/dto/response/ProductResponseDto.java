package com.alpha.olsp.dto.response;

import java.util.List;

public record ProductResponseDto(
        String id,
        String name,
        String description,
        Double price,
        Integer stock,
        List<String> imageUrls
) {
}
