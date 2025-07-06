package com.example.gameapp.models;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private LocalDate releaseDate;
    private String genre;
    private String platform;  // npr. PC, PS5, Xbox
    private String developer;
    private String publisher;

    private String coverImageUrl;
    private String youtubeTrailerUrl;
}
