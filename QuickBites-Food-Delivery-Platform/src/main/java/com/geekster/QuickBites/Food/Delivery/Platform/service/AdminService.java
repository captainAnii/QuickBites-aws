package com.geekster.QuickBites.Food.Delivery.Platform.service;

import com.geekster.QuickBites.Food.Delivery.Platform.model.*;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Dto.SignInInput;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Dto.SignUpOutput;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.AdminRepo;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.AuthTokenRepo;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.CraveCrafterRepo;
import com.geekster.QuickBites.Food.Delivery.Platform.repository.RestaurantRepo;
import com.geekster.QuickBites.Food.Delivery.Platform.service.emailUtility.EmailHandler;
import com.geekster.QuickBites.Food.Delivery.Platform.service.hashingUtility.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import com.geekster.QuickBites.Food.Delivery.Platform.model.Admin;

import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AuthTokenRepo authTokenRepo;
    @Autowired
    AdminRepo adminRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;
    @Autowired
    private CraveCrafterRepo craveCrafterRepo;

    // Service method to add a new restaurant
    public Restaurant addRestaurant(Restaurant restaurant) {

        return restaurantRepo.save(restaurant);
    }

    // Service method to delete a restaurant by ID
    public void deleteRestaurant(Long restaurantId) {

        restaurantRepo.deleteById(restaurantId);
    }

    // Service method to fetch a restaurant by ID
    public Restaurant getRestaurantById(Long restaurantId) {

        return restaurantRepo.findById(restaurantId).orElse(null);
    }

    // Service method to add a new crave crafter
    public CraveCrafter addCraveCrafter(CraveCrafter craveCrafter) {

        return craveCrafterRepo.save(craveCrafter);
    }

    // Service method to delete a crave crafter by ID
    public void deleteCraveCrafter(Long craveCrafterId) {
         craveCrafterRepo.deleteById(craveCrafterId);
    }

    // Service method to update a crave crafter
    public CraveCrafter updateCraveCrafter(Long craveCrafterId, CraveCrafter updatedCraveCrafter) {

        updatedCraveCrafter.setId(craveCrafterId);
        return craveCrafterRepo.save(updatedCraveCrafter);
    }

    public SignUpOutput signUpAdmin(Admin admin) {

        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = admin.getEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this customer email already exists ??
        Admin existingAdmin = adminRepo.findFirstByEmail(newEmail);

        if(existingAdmin != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncryptor.encryptPassword(admin.getPassword());

            //saveOrder the Customer with the new encrypted password

            admin.setPassword(encryptedPassword);
            adminRepo.save(admin);

            return new SignUpOutput(signUpStatus, "Admin registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String signInAdmin(SignInInput signInInput) {




            String signInStatusMessage = null;

            String signInEmail = signInInput.getEmail();

            if(signInEmail == null)
            {
                signInStatusMessage = "Invalid email";
                return signInStatusMessage;
            }

            //check if this customer email already exists ??
            Admin existingAdmin = adminRepo.findFirstByEmail(signInEmail);

            if(existingAdmin == null)
            {
                signInStatusMessage = "Email not registered!!!";
                return signInStatusMessage;

            }

            //match passwords :

            //hash the password: encrypt the password
            try {
                String encryptedPassword = PasswordEncryptor.encryptPassword(signInInput.getPassword());
                if(existingAdmin.getPassword().equals(encryptedPassword))
                {
                    //session should be created since password matched and user id is valid
                    AuthenticationToken authToken  = new AuthenticationToken(existingAdmin);
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
    }
