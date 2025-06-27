package com.Prakash.AirBnb.strategy;

import com.Prakash.AirBnb.entity.Inventory;

import java.math.BigDecimal;

public interface PricingStrategy {
    BigDecimal calculatePrice(Inventory inventory);
}
