package com.alpha.olsp.dto.response;

import com.alpha.olsp.model.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDto(
        String id,
        String customerId,
        List<OrderItemResponseDto> items,
        Double totalAmount,
        OrderStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
