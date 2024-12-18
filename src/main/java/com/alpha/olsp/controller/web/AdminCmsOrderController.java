package com.alpha.olsp.controller.web;

import com.alpha.olsp.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/cms/v1/admin/orders")
@RequiredArgsConstructor
public class AdminCmsOrderController {
    private static final Logger logger = LoggerFactory.getLogger(AdminCmsOrderController.class);
    private final OrderService orderService;

    @GetMapping
    public String listSellers(Model model) {
        logger.info("listSellers");
        // Add logic to fetch sellers
        model.addAttribute("orders", orderService.getOrders()); // Replace with actual list of sellers
        return "admin/orders";
    }
}
