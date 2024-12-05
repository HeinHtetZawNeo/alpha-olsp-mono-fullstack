package com.alpha.olsp.service;

import com.alpha.olsp.dto.request.OrderRequestDto;
import com.alpha.olsp.dto.response.OrderItemResponseDto;
import com.alpha.olsp.dto.response.OrderResponseDto;
import com.alpha.olsp.model.OrderItemStatus;
import com.alpha.olsp.model.OrderStatus;

import java.util.List;

public interface OrderService {
    OrderResponseDto createOrder(OrderRequestDto orderRequestDto, String token);

    List<OrderResponseDto> getOrders(String token);

    OrderResponseDto getOrderById(String id);

    void deleteOrder(String id);

    void updateOrderStatus(String orderId, OrderStatus status);

    void updateOrderItemStatus(String itemId, OrderItemStatus status);

    List<OrderItemResponseDto> getSellerOrders(String authorizationHeader);
}
