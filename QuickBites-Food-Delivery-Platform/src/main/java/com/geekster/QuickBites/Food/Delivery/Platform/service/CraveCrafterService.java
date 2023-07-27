package com.geekster.QuickBites.Food.Delivery.Platform.service;

import com.geekster.QuickBites.Food.Delivery.Platform.model.CraveCrafter;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.CraveCrafterRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CraveCrafterService {
    @Autowired
    CraveCrafterRepo craveCrafterRepo;
    public CraveCrafter addCraveCrafter(CraveCrafter craveCrafter) {
        return  craveCrafterRepo.save(craveCrafter);
    }

    public void deleteCraveCrafter(Long id) {
        craveCrafterRepo.deleteById(id);
    }



    public List<CraveCrafter> getAllCraveCrafters() {
        return craveCrafterRepo.findAll();
    }
}
