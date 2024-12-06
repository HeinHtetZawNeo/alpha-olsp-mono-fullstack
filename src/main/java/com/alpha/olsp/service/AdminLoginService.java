package com.alpha.olsp.service;

import com.alpha.olsp.dto.request.AdminLoginRequest;
import com.alpha.olsp.model.Admin;
import com.alpha.olsp.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminLoginService {

    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AdminLoginService.class);

    public boolean authenticateAdmin(AdminLoginRequest loginRequest) {
        logger.info("Authenticating Admin User");
        Optional<Admin> admin = adminRepository.findByEmail(loginRequest.getUsername());

        return passwordEncoder.matches(loginRequest.getPassword(), admin.get().getPassword());

    }
}
