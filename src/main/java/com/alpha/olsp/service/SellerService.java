package com.alpha.olsp.service;

import com.alpha.olsp.config.JwtService;
import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.exception.EmailAlreadyExistsException;
import com.alpha.olsp.model.Admin;
import com.alpha.olsp.model.Seller;
import com.alpha.olsp.repository.AdminRepository;
import com.alpha.olsp.repository.SellerRepository;
import com.alpha.olsp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;

    public AuthenticationResponseDto register(Seller seller) {
        //check the email
        userRepository.findByUsername(seller.getUsername())
                .ifPresent(x -> {
                    throw new EmailAlreadyExistsException("Email already exist");
                });
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));

        String token = jwtService.generateToken(sellerRepository.save(seller));
        return new AuthenticationResponseDto(token,seller.getEmail(),seller.getClass().getSimpleName().toUpperCase());
    }
}
