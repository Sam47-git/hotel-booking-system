package com.sam.project.AirBnb.strategy;

import com.sam.project.AirBnb.entities.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {

    BigDecimal calculatePrice(Inventory inventory);
}
