package com.example.gameapp.controllers;

import com.example.gameapp.models.Game;
import com.example.gameapp.models.User;
import com.example.gameapp.models.Wishlist;
import com.example.gameapp.repositories.GameRepository;
import com.example.gameapp.repositories.UserRepository;
import com.example.gameapp.repositories.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class WishlistController {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/wishlist/add/{gameId}")
    public String addToWishlist(@PathVariable Long gameId, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Game game = gameRepository.findById(gameId).orElseThrow();

        boolean alreadyExists = wishlistRepository.findByUserAndGame(user, game).isPresent();
        if (!alreadyExists) {
            Wishlist wishlist = new Wishlist();
            wishlist.setUser(user);
            wishlist.setGame(game);
            wishlistRepository.save(wishlist);
        }

        return "redirect:/games";
    }

    @GetMapping("/wishlist")
    public String viewWishlist(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Wishlist> wishlist = wishlistRepository.findByUser(user);
        model.addAttribute("wishlist", wishlist);
        return "wishlist";
    }

    @PostMapping("/wishlist/remove/{gameId}")
    public String removeFromWishlist(@PathVariable Long gameId, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        Game game = gameRepository.findById(gameId).orElseThrow();

        Wishlist wishlist = wishlistRepository.findByUserAndGame(user, game)
                .orElseThrow(() -> new RuntimeException("Wishlist entry not found"));

        wishlistRepository.delete(wishlist);

        return "redirect:/wishlist";
    }

}

