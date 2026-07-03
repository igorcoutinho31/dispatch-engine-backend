package com.example.demo.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.example.demo.domain.enums.VehicleCategory;
import com.example.demo.domain.strategy.DeliveryPriceStrategy;
import com.example.demo.domain.strategy.MotorcycleStrategy;
import com.example.demo.domain.strategy.PassengerCarStrategy;
import com.example.demo.domain.strategy.UtilityStrategy;

@Service
public class DeliveryPricingService {

    public BigDecimal calculate(VehicleCategory category, Double distanceInKm) {
        DeliveryPriceStrategy strategy;

        switch (category) {
            case MOTO ->
                strategy = new MotorcycleStrategy();
            case CARRO_PASSEIO ->
                strategy = new PassengerCarStrategy();
            case UTILITARIO ->
                strategy = new UtilityStrategy();
            default ->
                throw new IllegalArgumentException("Categoria de veículo não suportada para cálculo de frete.");
        }

        return strategy.calculatePrice(distanceInKm);
    }
}
