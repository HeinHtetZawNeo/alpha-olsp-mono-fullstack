package com.alpha.olsp.controller.web;

import com.alpha.olsp.dto.request.AdminLoginRequest;
import com.alpha.olsp.service.AdminLoginService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms/v1/admin")
@RequiredArgsConstructor

public class AdminCmsLoginController {

    private static final Logger logger = LoggerFactory.getLogger(AdminCmsLoginController.class);
    private final AdminLoginService adminLoginService;

    @GetMapping("/login")
    public String adminLogin() {
        logger.info("Admin login");
        return "admin/login";
    }

    @PostMapping("/login")
    public String processAdminLogin(AdminLoginRequest loginRequest, Model model) {
        logger.info("Admin login request: {}", loginRequest);
        System.out.println("In processAdminLogin");
        boolean isAuthenticated = adminLoginService.authenticateAdmin(loginRequest);
        if (isAuthenticated) {
            return "redirect:/cms/v1/admin/dashboard"; // Redirect to admin dashboard after login
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "admin/login";
        }
    }

    @GetMapping("/dashboard")
    public String adminDashboard() {
        return "admin/dashboard"; // Points to src/main/resources/templates/admin/dashboard.html
    }
}