package com.alpha.olsp.repository;

import com.alpha.olsp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByCustomerUserID(String customerId);

    @Query("SELECT o FROM Order o JOIN o.items i WHERE i.id = :orderItemId")
    Optional<Order> findByOrderItemId(@Param("orderItemId") String orderItemId);
}
