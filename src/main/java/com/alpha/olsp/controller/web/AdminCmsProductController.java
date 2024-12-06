package com.alpha.olsp.controller.web;

import com.alpha.olsp.model.Product;
import com.alpha.olsp.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cms/v1/admin/products")
@RequiredArgsConstructor
public class AdminCmsProductController {

    private static final Logger logger = LoggerFactory.getLogger(AdminCmsProductController.class);
    private final ProductService productService;

    @GetMapping
    public String listProduct(Model model) {
        logger.info("listProduct");
        // Add logic to fetch products
        model.addAttribute("products", productService.getAllProducts()); // Replace with actual list of sellers
        return "admin/products";
    }

    @GetMapping("/{id}")
    public String getProductDetails(@PathVariable String id, Model model) {
        logger.info("getProductDetails with id {}", id);
        Product product = productService.getProductByProductId(id); // Fetch the product by ID
        if (product.getSeller() == null) {
            System.out.println("Seller is null for product ID: " + id);
        }
        model.addAttribute("product", product);
        return "admin/product-details"; // Name of the Thymeleaf template
    }
}
