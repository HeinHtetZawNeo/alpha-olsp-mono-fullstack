package com.alpha.olsp.controller.rest;

import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.model.Admin;
import com.alpha.olsp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
    private final AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> adminRegister(@RequestBody Admin adminRequest) {
        logger.info("Admin register request: {}", adminRequest);
        AuthenticationResponseDto authenticationResponse = adminService.register(adminRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(authenticationResponse);
    }

    @GetMapping("/me")
    public String getProfile() {
        logger.info("getProfile");
        return "admin: secured end point";
    }
}
