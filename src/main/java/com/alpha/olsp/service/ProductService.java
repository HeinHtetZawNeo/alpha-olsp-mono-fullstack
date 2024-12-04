package com.alpha.olsp.service;

import com.alpha.olsp.config.JwtService;
import com.alpha.olsp.dto.request.ProductRegisterDto;
import com.alpha.olsp.dto.response.ProductDetailResponseDto;
import com.alpha.olsp.dto.response.ProductResponseDto;
import com.alpha.olsp.exception.InvalidInputException;
import com.alpha.olsp.exception.ProductAlreadyExistsException;
import com.alpha.olsp.mapper.ProductMapper;
import com.alpha.olsp.model.Catalog;
import com.alpha.olsp.model.Product;
import com.alpha.olsp.model.Seller;
import com.alpha.olsp.repository.CatalogRepository;
import com.alpha.olsp.repository.ProductRepository;
import com.alpha.olsp.repository.SellerRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final SellerRepository sellerRepository;
    private final CatalogRepository catalogRepository;
    private final BlobStorageService blobStorageService;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public List<ProductResponseDto> getAllProducts() {
        logger.info("getAllProducts");
        List<Product> products = productRepository.findAll();
        return products.stream().map(p -> ProductMapper.INSTANCE.toProductResponseDto(p)).collect(Collectors.toList());
    }

    public ProductResponseDto registerProduct(ProductRegisterDto productRegisterDto, String authorizationHeader, MultipartFile[] files) {
        logger.info("registerProduct {}", productRegisterDto);

        Seller seller = getSeller(authorizationHeader);
        Catalog catalog = catalogRepository.findById(productRegisterDto.catalogId())
                .orElseThrow(() -> new InvalidInputException("Invalid Catalog ID"));
        logger.info("FoundCatalog {}", catalog);

        // Validate Product already exists for this seller
        productRepository.findBySeller_UserIDAndName(seller.getUserID(), productRegisterDto.name())
                .ifPresent(product -> {
                    throw new ProductAlreadyExistsException(productRegisterDto.name() + " is already existed");
                });

        Product product = ProductMapper.INSTANCE.fromProductRegisterDto(productRegisterDto);
        product.setSeller(seller);
        product.setCatalog(catalog);

        // Store images in Azure Blob Storage
        List<String> imageUrls = blobStorageService.storeFilesInAzureBlob(files);
        product.setImageUrls(imageUrls); // Assuming you have an imageUrls field in the Product entity

        return ProductMapper.INSTANCE.toProductResponseDto(productRepository.save(product));
    }

    private Seller getSeller(String authorizationHeader) {
        String token = authorizationHeader.substring(7);
        Claims claims = jwtService.getClaims(token);
        String email = claims.getSubject();
        //Validate the Seller
        Seller seller = sellerRepository.findByEmail(email).orElseThrow(() -> {
            throw new InvalidInputException("Unauthorized access for this user");
        });
        return seller;
    }

    public ProductDetailResponseDto getProductDetails(String productid) {
        logger.info("getProductDetails {}", productid);
        Product product = productRepository.findById(productid).orElseThrow(()->{
            throw new InvalidInputException("Product not found");
        });
        return ProductMapper.INSTANCE.toProductDetailResponseDto(product);
    }
}
