package com.alpha.olsp.controller;

import com.alpha.olsp.dto.request.ProductRegisterDto;
import com.alpha.olsp.dto.response.CatalogResponseDto;
import com.alpha.olsp.dto.response.ProductResponseDto;
import com.alpha.olsp.service.CatalogService;
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
@RequestMapping("/api/v1/catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private final CatalogService catalogService;
    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);

    @GetMapping
    public ResponseEntity<List<CatalogResponseDto>> getCatalogs() {
        logger.info("Get catalogs");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(catalogService.getAllCatalogs());
    }
}
