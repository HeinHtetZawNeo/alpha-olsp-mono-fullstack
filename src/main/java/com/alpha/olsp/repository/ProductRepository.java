package com.alpha.olsp.repository;

import com.alpha.olsp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findBySeller_UserIDAndName(String seller_UserID, String name);
}
