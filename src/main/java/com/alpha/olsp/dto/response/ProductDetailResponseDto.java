package com.alpha.olsp.dto.response;

import java.time.LocalDateTime;
import java.util.List;

public record ProductDetailResponseDto(
        String id,
        String name,
        String description,
        Double price,
        Integer stock,
        String catalogId,
        String catalogName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<String> imageUrls
) {
}
