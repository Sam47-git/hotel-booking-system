package com.sam.project.AirBnb.strategy;

import com.sam.project.AirBnb.entities.Inventory;

import java.math.BigDecimal;


public class BasePricingStrategy implements PricingStrategy {

    @Override
    public BigDecimal calculatePrice(Inventory inventory) {
        return inventory.getRoom().getBasePrice();
    }
}
