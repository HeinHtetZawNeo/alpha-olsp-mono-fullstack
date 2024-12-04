package com.alpha.olsp.repository;

import com.alpha.olsp.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, String> {
    Optional<Product> findBySeller_UserIDAndName(String seller_UserID, String name);
    Page<Product> findByCatalogId(String catalogId, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Product> findByCatalogIdAndNameContainingIgnoreCase(String catalogId, String name, Pageable pageable);
}