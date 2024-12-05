package com.alpha.olsp.dto.request;

public record OrderItemRequestDto(
        String productId,
        Integer quantity
) {
}
