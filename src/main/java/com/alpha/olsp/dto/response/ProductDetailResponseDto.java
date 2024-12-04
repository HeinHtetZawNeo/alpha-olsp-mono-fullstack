package com.alpha.olsp.dto.response;

import com.alpha.olsp.model.Catalog;
import java.time.LocalDateTime;

public record ProductDetailResponseDto(
        String id,
        String name,
        String description,
        Double price,
        Integer stock,
        String catalogId,
        String catalogName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}