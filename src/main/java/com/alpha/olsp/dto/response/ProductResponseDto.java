package com.alpha.olsp.dto.response;

public record ProductResponseDto(
        String id,
        String name,
        String description,
        Double price,
        Integer stock
) {
}
