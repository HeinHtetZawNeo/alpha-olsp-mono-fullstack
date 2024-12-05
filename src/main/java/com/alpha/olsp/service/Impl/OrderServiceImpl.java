package com.alpha.olsp.service.Impl;

import com.alpha.olsp.dto.request.OrderRequestDto;
import com.alpha.olsp.dto.response.OrderResponseDto;
import com.alpha.olsp.exception.ResourceNotFoundException;
import com.alpha.olsp.mapper.OrderMapper;
import com.alpha.olsp.model.*;
import com.alpha.olsp.repository.CustomerRepository;
import com.alpha.olsp.repository.OrderRepository;
import com.alpha.olsp.repository.ProductRepository;
import com.alpha.olsp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Override
    public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
        Customer customer = customerRepository.findById(orderRequestDto.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        List<OrderItem> items = orderRequestDto.items().stream().map(itemRequest -> {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

            return new OrderItem(
                    null,
                    product,
                    itemRequest.quantity(),
                    product.getPrice() * itemRequest.quantity(),
                    OrderItemStatus.PENDING,
                    null,
                    null
            );
        }).collect(Collectors.toList());

        double totalAmount = items.stream().mapToDouble(OrderItem::getPrice).sum();

        Order order = new Order(
                null,
                customer,
                items,
                totalAmount,
                OrderStatus.CREATED,
                null,
                null
        );

        Order savedOrder = orderRepository.save(order);

        return OrderMapper.INSTANCE.toOrderResponseDto(savedOrder);
    }

    @Override
    public List<OrderResponseDto> getOrders() {
        return orderRepository.findAll().stream()
                .map(OrderMapper.INSTANCE::toOrderResponseDto)
                .collect(Collectors.toList());
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
