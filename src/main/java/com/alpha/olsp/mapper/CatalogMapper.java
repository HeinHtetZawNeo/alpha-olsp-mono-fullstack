package com.alpha.olsp.mapper;

import com.alpha.olsp.dto.request.ProductRegisterDto;
import com.alpha.olsp.dto.response.CatalogResponseDto;
import com.alpha.olsp.dto.response.ProductResponseDto;
import com.alpha.olsp.dto.response.SubCatalogResponseDto;
import com.alpha.olsp.model.Catalog;
import com.alpha.olsp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {
    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    @Mapping(target = "subCatalogs", source = "subCatalogs")
    CatalogResponseDto toCatalogResponseDto(Catalog catalog);

    List<CatalogResponseDto> toCatalogResponseDtos(List<Catalog> catalogs);
}
