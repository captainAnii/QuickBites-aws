package com.geekster.QuickBites.Food.Delivery.Platform.repository;

import com.geekster.QuickBites.Food.Delivery.Platform.model.AuthenticationToken;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Foodie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthTokenRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByFoodie(Foodie foodie);

}
