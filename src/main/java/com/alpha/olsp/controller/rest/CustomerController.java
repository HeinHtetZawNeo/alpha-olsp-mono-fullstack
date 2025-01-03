package com.alpha.olsp.controller.rest;

import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.model.Customer;
import com.alpha.olsp.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> adminRegister(@RequestBody Customer customerRequest) {
        logger.info("Customer register request: {}", customerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.register(customerRequest));
    }

    @GetMapping("/me")
    public String getProfile() {
        logger.info("getProfile");
        return "customer: secured end point";
    }
}