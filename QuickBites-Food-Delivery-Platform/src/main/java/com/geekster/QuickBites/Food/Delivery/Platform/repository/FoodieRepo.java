package com.geekster.QuickBites.Food.Delivery.Platform.repository;

import com.geekster.QuickBites.Food.Delivery.Platform.model.Foodie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodieRepo extends JpaRepository<Foodie,Long> {
    Foodie findFirstByFoodieEmail(String newEmail);

}
