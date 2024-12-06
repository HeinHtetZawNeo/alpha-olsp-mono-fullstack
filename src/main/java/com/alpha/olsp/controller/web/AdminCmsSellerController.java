package com.alpha.olsp.controller.web;

import com.alpha.olsp.service.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/cms/v1/admin/sellers")
@RequiredArgsConstructor
public class AdminCmsSellerController {

    private final SellerService sellerService;
    @GetMapping
    public String listSellers(Model model) {
        // Add logic to fetch sellers
        model.addAttribute("sellers", sellerService.getSellers()); // Replace with actual list of sellers
        return "admin/sellers";
    }
}
