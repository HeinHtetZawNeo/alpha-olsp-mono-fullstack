package com.alpha.olsp.mapper;

import com.alpha.olsp.dto.request.ProductRegisterDto;
import com.alpha.olsp.dto.response.ProductDetailResponseDto;
import com.alpha.olsp.dto.response.ProductResponseDto;
import com.alpha.olsp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Map Product to ProductResponseDto
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "imageUrls", source = "imageUrls")
    ProductResponseDto toProductResponseDto(Product product);

    // Map ProductRegisterDto to Product
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "catalog", ignore = true)
    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "imageUrls", ignore = true) // Assuming images will be set separately
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Product fromProductRegisterDto(ProductRegisterDto productRegisterDto);

    // Map Product to ProductDetailResponseDto
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "imageUrls", source = "imageUrls")
    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "catalog.name", target = "catalogName")
    @Mapping(target = "createdAt", source = "createdAt")
    @Mapping(target = "updatedAt", source = "updatedAt")
    ProductDetailResponseDto toProductDetailResponseDto(Product product);
}