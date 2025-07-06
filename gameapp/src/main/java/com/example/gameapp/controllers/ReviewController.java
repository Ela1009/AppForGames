package com.example.gameapp.controllers;

import com.example.gameapp.models.Game;
import com.example.gameapp.models.Review;
import com.example.gameapp.models.User;
import com.example.gameapp.models.enums.UserRole;
import com.example.gameapp.repositories.GameRepository;
import com.example.gameapp.repositories.ReviewRepository;
import com.example.gameapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ReviewController {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired private ReviewRepository reviewRepository;

    @GetMapping("/games/{gameId}/review")
    public String showReviewForm(@PathVariable Long gameId, Model model) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        model.addAttribute("game", game);
        model.addAttribute("review", new Review());
        return "review-form";
    }

    @PostMapping("/games/{gameId}/review")
    public String submitReview(@PathVariable Long gameId,
                               @ModelAttribute Review review,
                               Principal principal) {
        Game game = gameRepository.findById(gameId).orElseThrow();
        User user = userRepository.findByUsername(principal.getName());

        review.setGame(game);
        review.setUser(user);
        review.setCriticReview(user.getRole().equals(UserRole.REVIEWER)); // reviewer = true

        reviewRepository.save(review);
        return "redirect:/games";
    }

    @GetMapping("/games/{gameId}")
    public String viewGameDetails(@PathVariable Long gameId, Model model) {
        Game game = gameRepository.findById(gameId).orElseThrow();

        List<Review> criticReviews = reviewRepository.findByGameAndIsCriticReview(game, true);
        List<Review> userReviews = reviewRepository.findByGameAndIsCriticReview(game, false);

        model.addAttribute("game", game);
        model.addAttribute("criticReviews", criticReviews);
        model.addAttribute("userReviews", userReviews);

        return "game-details";
    }

    @GetMapping("/your-reviews")
    public String viewUserReviews(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Review> reviews = reviewRepository.findByUser(user);
        model.addAttribute("reviews", reviews);
        return "your-reviews";
    }
}

