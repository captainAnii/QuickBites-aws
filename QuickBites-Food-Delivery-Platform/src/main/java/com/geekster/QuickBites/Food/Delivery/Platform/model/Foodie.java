package com.geekster.QuickBites.Food.Delivery.Platform.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="customer")
public class Foodie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "foodieName is required")
    private String foodieName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String foodieEmail;
    @NotBlank(message = "Password is required")
    private String password;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "Contact number is required")
    private String contactNumber;
}
