package com.alpha.olsp.repository;

import com.alpha.olsp.model.Order;
import com.alpha.olsp.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, String> {
    @Query("SELECT oi FROM OrderItem oi WHERE oi.product.seller.userID = :sellerId")
    List<OrderItem> findAllBySellerId(@Param("sellerId") String sellerId);
}
