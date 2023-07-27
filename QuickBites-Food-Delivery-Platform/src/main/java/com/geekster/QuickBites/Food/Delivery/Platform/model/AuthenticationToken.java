package com.geekster.QuickBites.Food.Delivery.Platform.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthenticationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;
    private String tokenValue;
    private LocalDateTime tokenCreationDateTime;

    //mapping
    @OneToOne
    @JoinColumn(name = "fk_foodie_Id")
    Foodie foodie;

    @OneToOne
    @JoinColumn(name = "fk_admin_id")
    Admin admin;


    //create a parameterized constructor which takes foodie as an argument
    public AuthenticationToken(Foodie foodie)
    {
        this.foodie = foodie;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime = LocalDateTime.now();

    }
    //create a parameterized constructor which takes Admin as an argument

    public AuthenticationToken(Admin admin)
    {
        this.admin = admin;
        this.tokenValue = UUID.randomUUID().toString();
        this.tokenCreationDateTime = LocalDateTime.now();
    }
}