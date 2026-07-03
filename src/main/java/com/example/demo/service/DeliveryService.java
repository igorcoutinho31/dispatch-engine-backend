package com.example.demo.service;

import com.example.demo.controller.dto.DeliveryRequestDTO;
import com.example.demo.domain.Delivery;
import com.example.demo.domain.enums.DeliveryStatus;
import com.example.demo.repository.DeliveryRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryPricingService pricingService;
    private final UserRepository userRepository;

    public DeliveryService(DeliveryRepository deliveryRepository,
            DeliveryPricingService pricingService,
            UserRepository userRepository) {
        this.deliveryRepository = deliveryRepository;
        this.pricingService = pricingService;
        this.userRepository = userRepository;
    }

    public Delivery requestNewDelivery(DeliveryRequestDTO dto) {

        BigDecimal calculatedPrice = pricingService.calculate(dto.vehicleCategory(), dto.distanceInKm());

        var customer = userRepository.findById(dto.customerId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Delivery novaEntrega = new Delivery();
        novaEntrega.setCustomer(customer);
        novaEntrega.setPickupAddress(dto.pickupAddress());
        novaEntrega.setDropoffAddress(dto.dropoffAddress());
        novaEntrega.setPrice(calculatedPrice);
        novaEntrega.setStatus(DeliveryStatus.CRIADA);

        novaEntrega.setDestinationLatitude(dto.destinationLatitude());
        novaEntrega.setDestinationLongitude(dto.destinationLongitude());

        return deliveryRepository.save(novaEntrega);
    }
}
