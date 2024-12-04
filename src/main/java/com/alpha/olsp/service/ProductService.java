package com.alpha.olsp.service;

import com.alpha.olsp.dto.request.ProductRegisterDto;
import com.alpha.olsp.dto.response.ProductDetailResponseDto;
import com.alpha.olsp.dto.response.ProductResponseDto;
import com.alpha.olsp.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface ProductService {
    ProductResponseDto registerProduct(ProductRegisterDto productRegisterDto, String authorizationHeader, MultipartFile[] files);

    Seller getSeller(String authorizationHeader);

    ProductDetailResponseDto getProductDetails(String productid);

    Page<ProductResponseDto> getFilteredProducts(String catalogId, String productName, int page, int size, String sortBy, String sortDirection);
}
