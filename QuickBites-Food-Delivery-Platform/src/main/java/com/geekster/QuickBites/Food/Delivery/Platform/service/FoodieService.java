package com.geekster.QuickBites.Food.Delivery.Platform.service;

import com.geekster.QuickBites.Food.Delivery.Platform.model.AuthenticationToken;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Dto.SignInInput;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Dto.SignUpOutput;
import com.geekster.QuickBites.Food.Delivery.Platform.model.FeastFile;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Foodie;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.AuthTokenRepo;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.CraveCrafterRepo;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.FeastFileRepo;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.FoodieRepo;
import com.geekster.QuickBites.Food.Delivery.Platform.service.emailUtility.EmailHandler;
import com.geekster.QuickBites.Food.Delivery.Platform.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import com.geekster.QuickBites.Food.Delivery.Platform.model.enums.OrderStatus;
import org.springframework.stereotype.Service;


@Service
public class FoodieService {


    @Autowired
    FeastFileService feastFileService;
    @Autowired
    FoodieRepo foodieRepo;

    @Autowired
    AuthTokenRepo authTokenRepo;

    @Autowired
    CraveCrafterRepo craveCrafterRepo;


    public SignUpOutput signUpFoodie(Foodie foodie) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = foodie.getFoodieEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this customer email already exists ??
        Foodie existingFoodie = foodieRepo.findFirstByFoodieEmail(newEmail);

        if(existingFoodie != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncryptor.encryptPassword(foodie.getPassword());

            //saveOrder the Customer with the new encrypted password

            foodie.setPassword(encryptedPassword);
            foodieRepo.save(foodie);

            return new SignUpOutput(signUpStatus, "Foodie registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String signInFoodie(SignInInput signInInput) {


        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;
        }

        //check if this customer email already exists ??
        Foodie existingFoodie = foodieRepo.findFirstByFoodieEmail(signInEmail);

        if(existingFoodie == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncryptor.encryptPassword(signInInput.getPassword());
            if(existingFoodie.getPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingFoodie);
                authTokenRepo.save(authToken);

                EmailHandler.sendEmail(signInEmail,"email testing",authToken.getTokenValue());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }

    }

    public String signOutFoodie(String email) {

        Foodie foodie = foodieRepo.findFirstByFoodieEmail(email);
        authTokenRepo.delete(authTokenRepo.findFirstByFoodie(foodie));
        return "Customer Signed out successfully";
    }

    public boolean scheduleFeastFile(FeastFile feastFile) {

        Long foodItemId = feastFile.getCraveCrafter().getId();
        boolean isCraveCrafterValid = craveCrafterRepo.existsById(foodItemId);

        //id of customer
        Long foodieId = feastFile.getFoodie().getId();
        boolean isFoodieValid = foodieRepo.existsById(foodieId);

        if(isCraveCrafterValid && isFoodieValid)
        {
            feastFileService.saveFeastFile(feastFile);
            return true;
        }
        else {
            return false;
        }
    }

    public void cancelFeastFile(String email) {

        //email -> Customer
        Foodie foodie = foodieRepo.findFirstByFoodieEmail(email);

        FeastFile feastFile = feastFileService.getFeastFileForFoodie(foodie);

        feastFileService.cancelFeastFile(feastFile);



    }
}
