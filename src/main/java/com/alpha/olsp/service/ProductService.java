package com.alpha.olsp.service;

import com.alpha.olsp.dto.request.ProductRegisterDto;
import com.alpha.olsp.dto.response.ProductDetailResponseDto;
import com.alpha.olsp.dto.response.ProductResponseDto;
import com.alpha.olsp.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    ProductResponseDto registerProduct(ProductRegisterDto productRegisterDto, String authorizationHeader, MultipartFile[] files);

    ProductDetailResponseDto getProductDetails(String productid);

    Page<ProductResponseDto> getFilteredProducts(String catalogId, String productName, int page, int size, String sortBy, String sortDirection);

    List<Product> getAllProducts();

    Product getProductByProductId(String id);
}
