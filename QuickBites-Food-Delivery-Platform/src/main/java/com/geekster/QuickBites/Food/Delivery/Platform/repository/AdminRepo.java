package com.geekster.QuickBites.Food.Delivery.Platform.repository;

import com.geekster.QuickBites.Food.Delivery.Platform.model.Admin;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Foodie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin,Long> {

    Admin findFirstByEmail(String newEmail);
}
