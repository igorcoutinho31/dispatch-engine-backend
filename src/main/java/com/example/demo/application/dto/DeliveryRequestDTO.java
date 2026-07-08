package com.example.demo.application.dto;

import com.example.demo.domain.enums.VehicleCategory;

public record DeliveryRequestDTO(
        Long customerId,
        String pickupAddress,
        String dropoffAddress,
        VehicleCategory vehicleCategory
        ) {

}
