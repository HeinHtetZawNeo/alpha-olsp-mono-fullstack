package com.alpha.olsp.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    private String id = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // Customer who placed the order

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    @Column(nullable = false)
    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.CREATED;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Order(Customer customer, List<OrderItem> items, Double totalAmount, OrderStatus status) {
        this.customer = customer;
        this.items = items;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        } else if (updatedAt.isBefore(LocalDateTime.now())) {
            updatedAt = LocalDateTime.now();
        }
    }

    public void updateStatus() {
        if (this.items.stream().allMatch(item -> item.getStatus() == OrderItemStatus.DELIVERED)) {
            this.status = OrderStatus.DELIVERED;
        } else if (items.stream().anyMatch(item -> item.getStatus() == OrderItemStatus.SHIPPED || item.getStatus() == OrderItemStatus.PACKED)) {
            this.status = OrderStatus.PROCESSING;
        } else {
            this.status = OrderStatus.CREATED;
        }
    }
}