package com.geekster.QuickBites.Food.Delivery.Platform.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;
    @ManyToOne
    @JoinColumn(name = "foodie_id")
    private Foodie foodie;
    @Min(value = 1)
    @Max(value = 5)
    private int ratingValue; // The rating value (e.g., 1 to 5 stars)
    private String comment;
}