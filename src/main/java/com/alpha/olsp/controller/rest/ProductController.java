package com.alpha.olsp.controller.rest;

import com.alpha.olsp.dto.request.ProductRegisterDto;
import com.alpha.olsp.dto.response.ProductDetailResponseDto;
import com.alpha.olsp.dto.response.ProductResponseDto;
import com.alpha.olsp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponseDto>> getProducts(
            @RequestParam(required = false) String catalogId,
            @RequestParam(required = false) String productName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        logger.info("Get products with filters - catalogId: {}, productName: {}, page: {}, size: {}, sortBy: {}, sortDirection: {}",
                catalogId, productName, page, size, sortBy, sortDirection);

        Page<ProductResponseDto> products = productService.getFilteredProducts(catalogId, productName, page, size, sortBy, sortDirection);
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @PreAuthorize("hasRole('SELLER')")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ProductResponseDto> registerProduct(
            @ModelAttribute ProductRegisterDto productRegisterDto,
            @RequestParam("files") MultipartFile[] files,
            @RequestHeader("Authorization") String authorizationHeader) throws IOException {
        logger.info("Register product {}", productRegisterDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.registerProduct(productRegisterDto, authorizationHeader, files));
    }

    @GetMapping("/{productid}")
    public ResponseEntity<ProductDetailResponseDto> getProductDetail(@PathVariable("productid") String productid) {
        logger.info("Get product {}", productid);
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductDetails(productid));
    }
}
