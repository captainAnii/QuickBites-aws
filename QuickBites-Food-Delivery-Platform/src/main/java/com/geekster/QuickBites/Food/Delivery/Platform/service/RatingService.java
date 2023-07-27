package com.geekster.QuickBites.Food.Delivery.Platform.service;

import com.geekster.QuickBites.Food.Delivery.Platform.model.Rating;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.RatingRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RatingService {
    @Autowired
    RatingRepo ratingRepo;

    public Rating addRating(Rating rating) {
        return ratingRepo.save(rating);
    }

    public Optional<Rating> findRatingsById(Long id) {
        return ratingRepo.findById(id);
    }
}
