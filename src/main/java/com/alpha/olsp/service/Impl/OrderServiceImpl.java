package com.alpha.olsp.service.Impl;

import com.alpha.olsp.dto.request.OrderRequestDto;
import com.alpha.olsp.dto.response.OrderItemResponseDto;
import com.alpha.olsp.dto.response.OrderResponseDto;
import com.alpha.olsp.exception.ResourceNotFoundException;
import com.alpha.olsp.helper.Util;
import com.alpha.olsp.mapper.OrderMapper;
import com.alpha.olsp.model.*;
import com.alpha.olsp.repository.*;
import com.alpha.olsp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final Util util;
    private final OrderItemRepository orderItemRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto, String authorizationHeader) {
        // Get the customer from the token
        Customer customer = util.getCustomer(authorizationHeader);

        // Map OrderRequestDto items to OrderItem and update product quantities
        List<OrderItem> items = orderRequestDto.items().stream().map(itemRequest -> {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            // Check if the requested quantity is available
            if (product.getStock() < itemRequest.quantity()) {
                throw new IllegalArgumentException("Insufficient stock for product: " + product.getName());
            }

            // Update product quantity
            product.setStock(product.getStock() - itemRequest.quantity());
            productRepository.save(product); // Save the updated product quantity

            return new OrderItem(
                    product,
                    itemRequest.quantity(),
                    product.getPrice() * itemRequest.quantity(),
                    OrderItemStatus.PENDING
            );
        }).collect(Collectors.toList());

        // Calculate the total amount
        double totalAmount = items.stream().mapToDouble(OrderItem::getPrice).sum();

        // Create and save the order
        Order order = new Order(
                customer,
                items,
                totalAmount,
                OrderStatus.CREATED
        );

        Order savedOrder = orderRepository.save(order);

        // Return the response DTO
        return OrderMapper.INSTANCE.toOrderResponseDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getOrders(String authorizationHeader) {
        String email = util.getEmailFromToken(authorizationHeader);

        Optional<User> user = userRepository.findByUsername(email);
        User found = user.orElseThrow(() -> new ResourceNotFoundException("User not found"));

        List<Order> orders = List.of();
        switch (found.getClass().getSimpleName().toUpperCase()) {
            case "CUSTOMER":
                orders = orderRepository.findByCustomerUserID(found.getUserID());
                break;
            case "ADMIN":
                orders = orderRepository.findAll();
                break;
            default:
                throw new ResourceNotFoundException("Invalid role");
        }

        return orders.stream()
                .map(OrderMapper.INSTANCE::toOrderResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OrderItemResponseDto> getSellerOrders(String authorizationHeader) {
        Seller seller = util.getSeller(authorizationHeader);

        return orderItemRepository.findAllBySellerId(seller.getUserID()).stream().map(OrderMapper.INSTANCE::toOrderItemResponseDto).collect(Collectors.toList());
    }

    @Override
    public OrderResponseDto getOrderById(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        return OrderMapper.INSTANCE.toOrderResponseDto(order);
    }

    @Override
    public void deleteOrder(String id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found"));
        orderRepository.delete(order);
    }

    @Override
    public void updateOrderStatus(String orderId, OrderStatus status) {

    }

    @Override
    public void updateOrderItemStatus(String itemId, OrderItemStatus status) {

    }
}
