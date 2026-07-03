package com.example.demo.domain.strategy;

import java.math.BigDecimal;

public class PassengerCarStrategy implements DeliveryPriceStrategy {

    private static final BigDecimal BASE_FEE = new BigDecimal("8.00");
    private static final BigDecimal PRICE_PER_KM = new BigDecimal("2.50");

    @Override
    public BigDecimal calculatePrice(Double distanceInKm) {
        BigDecimal distance = BigDecimal.valueOf(distanceInKm);
        return BASE_FEE.add(distance.multiply(PRICE_PER_KM));
    }
}
