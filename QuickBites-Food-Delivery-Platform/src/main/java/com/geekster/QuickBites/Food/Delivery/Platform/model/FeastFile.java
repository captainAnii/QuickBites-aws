package com.geekster.QuickBites.Food.Delivery.Platform.model;

import com.geekster.QuickBites.Food.Delivery.Platform.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
public class FeastFile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "craveCrafter_id")
    private CraveCrafter craveCrafter;
    private OrderStatus status;
    private int quantity;
    private LocalDateTime orderScheduledTime;
    private LocalDateTime orderCreationTime;
    @ManyToOne
    @JoinColumn(name = "foodie_id")
    private Foodie foodie;
}
