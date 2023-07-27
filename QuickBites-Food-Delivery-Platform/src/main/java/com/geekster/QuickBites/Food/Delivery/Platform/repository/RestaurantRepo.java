package com.geekster.QuickBites.Food.Delivery.Platform.repository;

import com.geekster.QuickBites.Food.Delivery.Platform.model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepo extends JpaRepository<Restaurant,Long> {
}
