package com.alpha.olsp.service;

import com.alpha.olsp.config.JwtService;
import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.exception.EmailAlreadyExistsException;
import com.alpha.olsp.model.Admin;
import com.alpha.olsp.repository.AdminRepository;
import com.alpha.olsp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    public AuthenticationResponseDto register(Admin admin) {
        //check the email
        userRepository.findByUsername(admin.getUsername())
                .ifPresent(x -> {
                    throw new EmailAlreadyExistsException("Email already exist");
                });
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));

        String token = jwtService.generateToken(adminRepository.save(admin));
        return new AuthenticationResponseDto(token,admin.getEmail(),admin.getClass().getSimpleName().toUpperCase());
    }
}
