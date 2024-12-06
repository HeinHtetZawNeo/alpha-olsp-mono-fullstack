package com.alpha.olsp.controller.web;

import com.alpha.olsp.service.CatalogService;
import com.alpha.olsp.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cms/v1/admin/catalogs")
@RequiredArgsConstructor
public class AdminCmsCatalogController {

    private final CatalogService catalogService;
    @GetMapping
    public String listSellers(Model model) {
        // Add logic to fetch sellers
        model.addAttribute("catalogs", catalogService.getAllCatalogs()); // Replace with actual list of sellers
        return "admin/catalog";
    }
}
