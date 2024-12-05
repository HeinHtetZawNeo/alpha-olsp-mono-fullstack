package com.alpha.olsp.helper;

import com.alpha.olsp.config.JwtService;
import com.alpha.olsp.exception.InvalidInputException;
import com.alpha.olsp.model.Admin;
import com.alpha.olsp.model.Customer;
import com.alpha.olsp.model.Seller;
import com.alpha.olsp.repository.AdminRepository;
import com.alpha.olsp.repository.CustomerRepository;
import com.alpha.olsp.repository.SellerRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Util {

    private final JwtService jwtService;
    private final SellerRepository sellerRepository;
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;

    public Seller getSeller(String authorizationHeader) {
        String email = getEmailFromToken(authorizationHeader);
        return sellerRepository.findByEmail(email).orElseThrow(() -> {
            throw new InvalidInputException("Unauthorized access for this seller");
        });
    }

    public Customer getCustomer(String authorizationHeader) {
        String email = getEmailFromToken(authorizationHeader);
        return customerRepository.findByEmail(email).orElseThrow(() -> {
            throw new InvalidInputException("Unauthorized access for this customer");
        });
    }

    public Admin getAdmin(String authorizationHeader) {
        String email = getEmailFromToken(authorizationHeader);
        return adminRepository.findByEmail(email).orElseThrow(() -> {
            throw new InvalidInputException("Unauthorized access for this admin");
        });
    }

    public String getEmailFromToken(String authorizationHeader) {
        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix
        Claims claims = jwtService.getClaims(token); // Get claims using the JwtService
        return claims.getSubject(); // Extract the subject (email)
    }
}