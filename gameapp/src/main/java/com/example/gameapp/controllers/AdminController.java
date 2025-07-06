package com.example.gameapp.controllers;

import com.example.gameapp.models.Game;
import com.example.gameapp.models.User;
import com.example.gameapp.models.enums.UserRole;
import com.example.gameapp.repositories.GameRepository;
import com.example.gameapp.services.GameService;
import com.example.gameapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @GetMapping("/user-management")
    public String showUserManagement(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "user-management";
    }

    @PostMapping("/promote/{id}")
    public String promoteUserToReviewer(@PathVariable Long id) {
        userService.promoteToReviewer(id);
        return "redirect:/admin/user-management";
    }

    @GetMapping("/add-user")
    public String showAddUserForm() {
        return "add-user";
    }

    @PostMapping("/add-user")
    public String addUser(@RequestParam String username,
                          @RequestParam String email,
                          @RequestParam String password,
                          @RequestParam UserRole role) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setRole(role);
        user.setCreatedAt(LocalDateTime.now());
        userService.saveUser(user);

        return "redirect:/admin/user-management";
    }




    @GetMapping("/add-game")
    public String showAddGameForm() {
        return "add-game";
    }

    @PostMapping("/add-game")
    public String addGame(@RequestParam String title,
                          @RequestParam String description,
                          @RequestParam String releaseDate,
                          @RequestParam String genre,
                          @RequestParam String platform,
                          @RequestParam String developer,
                          @RequestParam String publisher,
                          @RequestParam(required = false) String coverImageUrl,
                          @RequestParam(required = false) String youtubeTrailerUrl) {

        Game game = new Game();
        game.setTitle(title);
        game.setDescription(description);
        game.setReleaseDate(LocalDate.parse(releaseDate));
        game.setGenre(genre);
        game.setPlatform(platform);
        game.setDeveloper(developer);
        game.setPublisher(publisher);
        game.setCoverImageUrl(coverImageUrl);
        game.setYoutubeTrailerUrl(youtubeTrailerUrl);

        gameRepository.save(game);
        return "redirect:/admin/dashboard";
    }


    @GetMapping("/all-games")
    public String viewAllGames(Model model) {
        model.addAttribute("games", gameService.getAllGames());
        return "all-games";  // moraš imati all-games.html
    }


}
