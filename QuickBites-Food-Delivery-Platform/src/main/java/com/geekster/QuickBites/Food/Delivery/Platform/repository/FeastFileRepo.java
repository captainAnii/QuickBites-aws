package com.geekster.QuickBites.Food.Delivery.Platform.repository;

import com.geekster.QuickBites.Food.Delivery.Platform.model.FeastFile;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Foodie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeastFileRepo  extends JpaRepository<FeastFile, Long> {
    FeastFile findFirstByFoodie(Foodie foodie);

}
