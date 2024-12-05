package com.alpha.olsp.dto.request;

import java.util.List;

public record OrderRequestDto(
        String customerId,
        List<OrderItemRequestDto> items
) {
}
