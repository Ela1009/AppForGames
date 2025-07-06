package com.example.gameapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/critic")
public class ReviewerDashboardController {

    @GetMapping("/dashboard")
    public String showReviewerDashboard() {
        return "reviewer-dashboard"; // Thymeleaf template: reviewer-dashboard.html
    }
}
