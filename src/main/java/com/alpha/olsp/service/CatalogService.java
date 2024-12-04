package com.alpha.olsp.service;

import com.alpha.olsp.dto.response.CatalogResponseDto;

import java.util.List;

public interface CatalogService {
    List<CatalogResponseDto> getAllCatalogs();
}
