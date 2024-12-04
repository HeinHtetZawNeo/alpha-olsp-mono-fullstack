package com.alpha.olsp.service.Impl;

import com.alpha.olsp.dto.response.CatalogResponseDto;
import com.alpha.olsp.mapper.CatalogMapper;
import com.alpha.olsp.model.Catalog;
import com.alpha.olsp.repository.CatalogRepository;
import com.alpha.olsp.service.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CatalogServiceImpl implements CatalogService {
    private final CatalogRepository catalogRepository;

    @Override
    public List<CatalogResponseDto> getAllCatalogs() {
        List<Catalog> catalogs = catalogRepository.findAllByParentCatalogIsNull();

        return CatalogMapper.INSTANCE.toCatalogResponseDtos(catalogs);
    }
}
