package com.geekster.QuickBites.Food.Delivery.Platform.service;

import com.geekster.QuickBites.Food.Delivery.Platform.model.FeastFile;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Foodie;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.FeastFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FeastFileService {
    @Autowired

    FeastFileRepo feastFileRepo;
    public  List<FeastFile> getAllFeastFile() {
        return feastFileRepo.findAll();
    }

    public void saveFeastFile(FeastFile feastFile) {

        feastFile.setOrderCreationTime(LocalDateTime.now());
        feastFileRepo.save(feastFile);
    }

    public FeastFile getFeastFileForFoodie(Foodie foodie) {
        return feastFileRepo.findFirstByFoodie(foodie);
    }

    public void cancelFeastFile(FeastFile feastFile) {

        feastFileRepo.delete(feastFile);
    }
}
