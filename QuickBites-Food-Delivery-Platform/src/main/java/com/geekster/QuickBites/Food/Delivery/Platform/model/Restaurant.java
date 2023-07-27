package com.geekster.QuickBites.Food.Delivery.Platform.model;

import com.geekster.QuickBites.Food.Delivery.Platform.model.enums.RestaurantStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    private String speciality;
    private RestaurantStatus status;
    private double averageRating; // Overall rating based on all ratings received
    private int totalRatings;
}
