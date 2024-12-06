package com.alpha.olsp.controller.rest;


import com.alpha.olsp.dto.request.OrderRequestDto;
import com.alpha.olsp.dto.response.OrderItemResponseDto;
import com.alpha.olsp.dto.response.OrderResponseDto;
import com.alpha.olsp.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.alpha.olsp.model.OrderItemStatus;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    @PreAuthorize("hasRole('CUSTOMER')")
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto, @RequestHeader("Authorization") String authorizationHeader) {
        logger.info("Create order: {}", orderRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderRequestDto, authorizationHeader));
    }

    @PreAuthorize("hasAnyRole('CUSTOMER','ADMIN')")
    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrders(@RequestHeader("Authorization") String authorizationHeader) {
        logger.info("Get all orders");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrders(authorizationHeader));
    }
    @PreAuthorize("hasRole('SELLER')")
    @GetMapping("/items")
    public ResponseEntity<List<OrderItemResponseDto>> getSellerOrders(@RequestHeader("Authorization") String authorizationHeader) {
        logger.info("Get all seller orders");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getSellerOrders(authorizationHeader));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable String id,@RequestHeader("Authorization") String authorizationHeader) {
        logger.info("Get order by ID: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id,authorizationHeader));
    }

    @PreAuthorize("hasRole('SELLER')")
    @PatchMapping("/items/{itemId}/status")
    public ResponseEntity<Void> updateOrderItemStatus(
            @PathVariable String itemId,
            @RequestParam OrderItemStatus status,
            @RequestHeader("Authorization") String authorizationHeader) {
        logger.info("Update status of order item {} to {}", itemId, status);
        orderService.updateOrderItemStatus(itemId, status,authorizationHeader);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}