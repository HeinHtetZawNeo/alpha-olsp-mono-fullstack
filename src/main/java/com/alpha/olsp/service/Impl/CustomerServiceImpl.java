package com.alpha.olsp.service.Impl;

import com.alpha.olsp.config.JwtService;
import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.exception.EmailAlreadyExistsException;
import com.alpha.olsp.model.Customer;
import com.alpha.olsp.repository.CustomerRepository;
import com.alpha.olsp.repository.UserRepository;
import com.alpha.olsp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final CustomerRepository customerRepository;
    private final UserRepository userRepository;

    @Override
    public AuthenticationResponseDto register(Customer customer) {
        //check the email
        userRepository.findByUsername(customer.getUsername())
                .ifPresent(x -> {
                    throw new EmailAlreadyExistsException("Email already exist");
                });
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));

        String token = jwtService.generateToken(customerRepository.save(customer));
        return new AuthenticationResponseDto(token, customer.getEmail(), customer.getClass().getSimpleName().toUpperCase());
    }
}
