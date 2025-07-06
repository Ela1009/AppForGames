package com.example.gameapp.repositories;


import com.example.gameapp.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}

