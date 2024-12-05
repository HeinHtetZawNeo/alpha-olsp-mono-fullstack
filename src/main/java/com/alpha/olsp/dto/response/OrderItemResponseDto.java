package com.alpha.olsp.dto.response;

import com.alpha.olsp.model.OrderItemStatus;

public record OrderItemResponseDto(
        String id,
        String productId,
        String productName,
        Integer quantity,
        Double price,
        OrderItemStatus status
) {
}
