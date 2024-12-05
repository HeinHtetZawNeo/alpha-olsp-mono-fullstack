package com.alpha.olsp.controller;


import com.alpha.olsp.dto.request.OrderRequestDto;
import com.alpha.olsp.dto.response.OrderResponseDto;
import com.alpha.olsp.model.OrderItemStatus;
import com.alpha.olsp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @PreAuthorize("hasRole('CUSTOMER')")
    @PatchMapping("/")
    @PostMapping
    public ResponseEntity<OrderResponseDto> createOrder(@Valid @RequestBody OrderRequestDto orderRequestDto) {
        logger.info("Create order: {}", orderRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orderService.createOrder(orderRequestDto));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDto>> getOrders(@RequestHeader("Authorization") String authorizationHeader) {
        logger.info("Get all orders");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable String id) {
        logger.info("Get order by ID: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        logger.info("Delete order by ID: {}", id);
        orderService.deleteOrder(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }


    @PreAuthorize("hasRole('SELLER')")
    @PatchMapping("/items/{itemId}/status")
    public ResponseEntity<Void> updateOrderItemStatus(
            @PathVariable String itemId,
            @RequestParam OrderItemStatus status) {
        logger.info("Update status of order item {} to {}", itemId, status);
        orderService.updateOrderItemStatus(itemId, status);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}