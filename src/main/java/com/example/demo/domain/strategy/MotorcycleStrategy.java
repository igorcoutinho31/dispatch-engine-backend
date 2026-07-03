package com.example.demo.domain.strategy;

import java.math.BigDecimal;

public class MotorcycleStrategy implements DeliveryPriceStrategy {
    private static final BigDecimal BASE_FEE = new BigDecimal("5.00");
    private static final BigDecimal PRICE_PER_KM = new BigDecimal("1.20");

    @Override
    public BigDecimal calculatePrice(Double distanceInKm) {
        BigDecimal distance = BigDecimal.valueOf(distanceInKm);
        return BASE_FEE.add(distance.multiply(PRICE_PER_KM));
    }
}
