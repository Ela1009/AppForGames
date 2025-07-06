package com.example.gameapp.repositories;

import com.example.gameapp.models.Wishlist;
import com.example.gameapp.models.User;
import com.example.gameapp.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    List<Wishlist> findByUser(User user);

    Optional<Wishlist> findByUserAndGame(User user, Game game);

    void deleteByUserAndGame(User user, Game game);
}
