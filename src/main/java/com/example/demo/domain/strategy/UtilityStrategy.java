package com.example.demo.domain.strategy;

import java.math.BigDecimal;

public class UtilityStrategy implements DeliveryPriceStrategy {

    private static final BigDecimal BASE_FEE = new BigDecimal("25.00");
    private static final BigDecimal PRICE_PER_KM = new BigDecimal("4.00");

    @Override
    public BigDecimal calculatePrice(Double distanceInKm) {
        BigDecimal distance = BigDecimal.valueOf(distanceInKm);
        return BASE_FEE.add(distance.multiply(PRICE_PER_KM));
    }
}
