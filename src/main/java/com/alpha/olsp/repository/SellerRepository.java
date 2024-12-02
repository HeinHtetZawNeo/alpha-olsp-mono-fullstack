package com.alpha.olsp.repository;

import com.alpha.olsp.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<Seller, String> {
    Optional<Seller> findByEmail(String email);
}
