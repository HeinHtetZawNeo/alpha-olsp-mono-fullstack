package com.alpha.olsp.dto.request;

import java.util.List;

public record OrderRequestDto(
        List<OrderItemRequestDto> items
) {
}
