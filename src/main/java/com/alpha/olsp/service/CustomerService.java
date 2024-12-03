package com.alpha.olsp.service;

import com.alpha.olsp.config.JwtService;
import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.exception.EmailAlreadyExistsException;
import com.alpha.olsp.model.Customer;
import com.alpha.olsp.model.Seller;
import com.alpha.olsp.repository.CustomerRepository;
import com.alpha.olsp.repository.SellerRepository;
import com.alpha.olsp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    public AuthenticationResponseDto register(Customer customer) {
        //check the email
        userRepository.findByUsername(customer.getUsername())
                .ifPresent(x -> {
                    throw new EmailAlreadyExistsException("Email already exist");
                });
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        String token = jwtService.generateToken(customerRepository.save(customer));
        return new AuthenticationResponseDto(token,customer.getEmail(),customer.getClass().getSimpleName().toUpperCase());
    }
}
