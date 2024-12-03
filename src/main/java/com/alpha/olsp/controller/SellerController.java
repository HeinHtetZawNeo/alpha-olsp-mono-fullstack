package com.alpha.olsp.controller;

import com.alpha.olsp.dto.response.AuthenticationResponseDto;
import com.alpha.olsp.model.Seller;
import com.alpha.olsp.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/seller")
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private static final Logger logger = LoggerFactory.getLogger(SellerController.class);

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponseDto> adminRegister(@RequestBody Seller sellerRequest) {
        logger.info("Seller register request: {}", sellerRequest);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(sellerService.register(sellerRequest));
    }

    @GetMapping("/me")
    public String getProfile() {
        logger.info("getProfile");
        return "seller: secured end point";
    }
}