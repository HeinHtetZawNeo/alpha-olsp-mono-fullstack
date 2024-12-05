package com.alpha.olsp.service.Impl;

import com.alpha.olsp.dto.request.CatalogRequestDto;
import com.alpha.olsp.dto.response.CatalogResponseDto;
import com.alpha.olsp.exception.InvalidInputException;
import com.alpha.olsp.exception.ResourceNotFoundException;
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
    private final CatalogMapper catalogMapper = CatalogMapper.INSTANCE;

    @Override
    public List<CatalogResponseDto> getAllCatalogs() {
        List<Catalog> catalogs = catalogRepository.findAllByParentCatalogIsNull();

        return CatalogMapper.INSTANCE.toCatalogResponseDtos(catalogs);
    }

    @Override
    public CatalogResponseDto getCatalogById(String id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found with ID: " + id));
        return catalogMapper.toCatalogResponseDto(catalog);
    }

    @Override
    public CatalogResponseDto createCatalog(CatalogRequestDto catalogRequestDto) {
        Catalog catalog = catalogMapper.fromCatalogRequestDto(catalogRequestDto);

        if (catalogRequestDto.parentCatalogId() != null) {
            catalog.setParentCatalog(catalogRepository.findById(catalogRequestDto.parentCatalogId())
                    .orElseThrow(() -> new ResourceNotFoundException("Catalog not found with ID: " + catalogRequestDto.parentCatalogId())));
        }


        if (catalogRepository.existsByName(catalog.getName())) {
            throw new InvalidInputException("Catalog name already exists");
        }
        catalog = catalogRepository.save(catalog);
        return catalogMapper.toCatalogResponseDto(catalog);
    }

    @Override
    public CatalogResponseDto updateCatalog(String id, CatalogRequestDto catalogRequestDto) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found with ID: " + id));

        if (!catalog.getProducts().isEmpty())
            throw new InvalidInputException("Catalog has products");

        if (catalogRepository.existsByName(catalogRequestDto.name())) {
            throw new InvalidInputException("Catalog name already exists");
        }

        catalog.setName(catalogRequestDto.name());
        catalog.setDescription(catalogRequestDto.description());
        catalog = catalogRepository.save(catalog);

        return catalogMapper.toCatalogResponseDto(catalog);
    }

    @Override
    public void deleteCatalog(String id) {
        Catalog catalog = catalogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catalog not found with ID: " + id));
        if (!catalog.getProducts().isEmpty())
            throw new InvalidInputException("Catalog has products");
        catalogRepository.delete(catalog);
    }
}
