package com.example.gameapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminDashboardController {

    @GetMapping("/dashboard")
    public String showAdminDashboard() {
        return "admin-dashboard"; // Thymeleaf template: admin-dashboard.html
    }
}
