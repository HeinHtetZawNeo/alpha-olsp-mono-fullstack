package com.alpha.olsp.service.Impl;

import com.alpha.olsp.config.JwtService;
import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.exception.EmailAlreadyExistsException;
import com.alpha.olsp.model.Seller;
import com.alpha.olsp.repository.SellerRepository;
import com.alpha.olsp.repository.UserRepository;
import com.alpha.olsp.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final SellerRepository sellerRepository;
    private final UserRepository userRepository;

    @Override
    public AuthenticationResponseDto register(Seller seller) {
        //check the email
        userRepository.findByUsername(seller.getUsername())
                .ifPresent(x -> {
                    throw new EmailAlreadyExistsException("Email already exist");
                });
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));

        String token = jwtService.generateToken(sellerRepository.save(seller));
        return new AuthenticationResponseDto(token, seller.getEmail(), seller.getClass().getSimpleName().toUpperCase());
    }

    @Override
    public List<Seller> getSellers() {

        return sellerRepository.findAll();
    }
}
