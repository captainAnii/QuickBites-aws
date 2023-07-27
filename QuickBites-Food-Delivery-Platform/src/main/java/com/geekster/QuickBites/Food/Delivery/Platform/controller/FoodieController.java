package com.geekster.QuickBites.Food.Delivery.Platform.controller;

import com.geekster.QuickBites.Food.Delivery.Platform.model.*;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Dto.SignInInput;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Dto.SignUpOutput;
import com.geekster.QuickBites.Food.Delivery.Platform.service.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/foodie")
public class FoodieController {
    @Autowired
    FeastFileService feastFileService;

    @Autowired
    RatingService ratingService;

    @Autowired
    RestaurantService restaurantService;

    @Autowired
    FoodieService foodieService;
    @Autowired
    AuthTokenService authTokenService;

    @Autowired
    CraveCrafterService craveCrafterService;


    @PostMapping("foodie/signIn")
    public String signInFoodie(@RequestBody @Valid SignInInput signInInput)
    {
        return foodieService.signInFoodie(signInInput);
    }

    @PostMapping("foodie/signup")
    public SignUpOutput signUpFoodie(@RequestBody Foodie foodie)
    {

        return foodieService.signUpFoodie(foodie);
    }
    @DeleteMapping("foodie/signOut")
    public String signOutFoodie(String email, String token)
    {
        if(authTokenService.authenticate(email,token)) {
            return foodieService.signOutFoodie(email);
        }
        else {
            return "Sign out not allowed for non authenticated foodie.";
        }

    }

    @PostMapping("feastFile/schedule")
    public String  scheduleFeastFile(@RequestBody FeastFile feastFile, String email, String token)
    {

        if(authTokenService.authenticate(email,token)) {
            boolean status = foodieService.scheduleFeastFile(feastFile);
            return status ? "Order scheduled":"error occurred during scheduling order";
        }
        else
        {
            return "Scheduling failed because invalid authentication";
        }
    }

    @DeleteMapping("feastFile/cancel")
    public String  cancelFeastFile(String email, String token)
    {

        if(authTokenService.authenticate(email,token)) {
            foodieService.cancelFeastFile(email);
            return "cancelled feastFile successfully";
        }
        else
        {
            return "Scheduling failed because invalid authentication";
        }
    }
    @GetMapping("/restaurants")
    public List<Restaurant> getAllRestaurants() {
        return restaurantService.getAllRestaurants();
    }
    @GetMapping("/craveCrafters")
    public List<CraveCrafter> getAllCraveCrafters(){
        return craveCrafterService.getAllCraveCrafters();
    }

    @PostMapping("/rating")
    public Rating addRating(@RequestBody Rating rating) {
        return ratingService.addRating(rating);
    }

    @GetMapping("/ratings/restaurant/{id}")
    public Optional<Rating> getRatingsByRestaurant(@PathVariable Long id) {

        // Return the list of ratings with HttpStatus.OK status
        return ratingService.findRatingsById(id);
    }

    @GetMapping("feastFile")
    List<FeastFile> getAllFeastFile()
    {
        return feastFileService.getAllFeastFile();
    }

}


