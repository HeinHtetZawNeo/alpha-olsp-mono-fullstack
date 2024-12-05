package com.alpha.olsp.service;

import com.alpha.olsp.dto.request.CatalogRequestDto;
import com.alpha.olsp.dto.response.CatalogResponseDto;

import java.util.List;

public interface CatalogService {
    List<CatalogResponseDto> getAllCatalogs();
    CatalogResponseDto getCatalogById(String id);
    CatalogResponseDto createCatalog(CatalogRequestDto catalogRequestDto);
    CatalogResponseDto updateCatalog(String id, CatalogRequestDto catalogRequestDto);
    void deleteCatalog(String id);
}
