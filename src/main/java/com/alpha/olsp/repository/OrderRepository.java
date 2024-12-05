package com.alpha.olsp.repository;

import com.alpha.olsp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCustomerUserID(String customerId);
}
