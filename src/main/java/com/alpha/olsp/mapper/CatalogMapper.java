package com.alpha.olsp.mapper;

import com.alpha.olsp.dto.response.CatalogResponseDto;
import com.alpha.olsp.model.Catalog;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CatalogMapper {
    CatalogMapper INSTANCE = Mappers.getMapper(CatalogMapper.class);

    //@Mapping(target = "subCatalogs", source = "subCatalogs")
    CatalogResponseDto toCatalogResponseDto(Catalog catalog);

    List<CatalogResponseDto> toCatalogResponseDtos(List<Catalog> catalogs);
}
