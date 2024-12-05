package com.alpha.olsp.controller;

import com.alpha.olsp.dto.request.CatalogRequestDto;
import com.alpha.olsp.dto.response.CatalogResponseDto;
import com.alpha.olsp.service.CatalogService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/catalogs")
@RequiredArgsConstructor
public class CatalogController {
    private static final Logger logger = LoggerFactory.getLogger(CatalogController.class);
    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<CatalogResponseDto>> getCatalogs() {
        logger.info("Get catalogs");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(catalogService.getAllCatalogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CatalogResponseDto> getCatalogById(@PathVariable String id) {
        logger.info("Get catalog by ID: {}", id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(catalogService.getCatalogById(id));
    }

    @PostMapping
    public ResponseEntity<CatalogResponseDto> createCatalog(@Valid @RequestBody CatalogRequestDto catalogRequestDto) {
        logger.info("Create catalog: {}", catalogRequestDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(catalogService.createCatalog(catalogRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CatalogResponseDto> updateCatalog(
            @PathVariable String id,
            @Valid @RequestBody CatalogRequestDto catalogRequestDto) {
        logger.info("Update catalog ID: {}, Data: {}", id, catalogRequestDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(catalogService.updateCatalog(id, catalogRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCatalog(@PathVariable String id) {
        logger.info("Delete catalog by ID: {}", id);
        catalogService.deleteCatalog(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
