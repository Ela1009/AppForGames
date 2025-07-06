package com.example.gameapp.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private User user;

    @ManyToOne(optional = false)
    private Game game;

    private int rating; // 1–10

    @Column(columnDefinition = "TEXT")
    private String reviewText;

    private boolean isCriticReview;
}
