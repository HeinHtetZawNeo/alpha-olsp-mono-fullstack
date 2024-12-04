package com.alpha.olsp.mapper;

import com.alpha.olsp.dto.request.ProductRegisterDto;
import com.alpha.olsp.dto.response.ProductDetailResponseDto;
import com.alpha.olsp.dto.response.ProductResponseDto;
import com.alpha.olsp.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.alpha.olsp.model.Order;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    // Map Product to ProductDTO
    //@Mapping(source = "orderDate", target = "orderDate", dateFormat = "yyyy-MM-dd")
    ProductResponseDto toProductResponseDto(Product product);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    @Mapping(target = "catalog", ignore = true)
    Product fromProductRegisterDto(ProductRegisterDto productRegisterDto);

    @Mapping(target = "name", source = "name")
    @Mapping(target = "description", source = "description")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "stock", source = "stock")
    @Mapping(source = "catalog.id", target = "catalogId")
    @Mapping(source = "catalog.name", target = "catalogName")
    ProductDetailResponseDto toProductDetailResponseDto(Product product);
}
