package com.geekster.QuickBites.Food.Delivery.Platform.service;

import com.geekster.QuickBites.Food.Delivery.Platform.model.Restaurant;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.RestaurantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {
    @Autowired
    RestaurantRepo restaurantRepo;
    public Restaurant addRestaurant(Restaurant restaurant) {
        return restaurantRepo.save(restaurant);
    }

    public void deleteRestaurant(Long id) {
        restaurantRepo.deleteById(id);
    }

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }
}
