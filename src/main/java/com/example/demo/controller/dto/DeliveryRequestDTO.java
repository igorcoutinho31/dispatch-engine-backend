package com.example.demo.controller.dto;

import com.example.demo.domain.enums.VehicleCategory;

public record DeliveryRequestDTO(
        Long customerId,
        String pickupAddress,
        String dropoffAddress,
        VehicleCategory vehicleCategory,
        Double distanceInKm,
        Double destinationLatitude,
        Double destinationLongitude
        ) {

}
