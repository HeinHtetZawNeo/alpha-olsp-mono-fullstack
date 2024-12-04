package com.alpha.olsp.controller;

import com.alpha.olsp.dto.request.ProductRegisterDto;
import com.alpha.olsp.dto.response.ProductDetailResponseDto;
import com.alpha.olsp.dto.response.ProductResponseDto;
import com.alpha.olsp.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @GetMapping
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        logger.info("Get products");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productService.getAllProducts());
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping
    public ResponseEntity<ProductResponseDto> registerProduct(@Valid @RequestBody ProductRegisterDto productRegisterDto, @RequestHeader("Authorization") String authorizationHeader) throws BadRequestException {
        logger.info("Register product {}", productRegisterDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.registerProduct(productRegisterDto,authorizationHeader));
    }

    @GetMapping("/{productid}")
    public ResponseEntity<ProductDetailResponseDto> getProductDetail(@PathVariable("productid") String productid) {
        logger.info("Get product {}", productid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductDetails(productid));
    }
}
