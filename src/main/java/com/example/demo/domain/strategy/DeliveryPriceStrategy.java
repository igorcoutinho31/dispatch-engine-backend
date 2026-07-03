package com.example.demo.domain.strategy;

import java.math.BigDecimal;

public interface DeliveryPriceStrategy {

    BigDecimal calculatePrice(Double distanceInKm);
}
