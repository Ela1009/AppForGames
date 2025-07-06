package com.example.gameapp.repositories;

import com.example.gameapp.models.Game;
import com.example.gameapp.models.Review;
import com.example.gameapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Sve recenzije za igru po tipu (user ili critic)
    List<Review> findByGameAndIsCriticReview(Game game, boolean isCriticReview);

    // Sve recenzije koje je napisao korisnik
    List<Review> findByUser(User user);



}
