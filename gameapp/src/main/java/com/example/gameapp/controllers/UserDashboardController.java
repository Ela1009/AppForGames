package com.example.gameapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserDashboardController {

    @GetMapping("/user/dashboard")
    public String showUserDashboard() {
        return "user-dashboard"; // Thymeleaf će tražiti user-dashboard.html u /templates
    }
}
