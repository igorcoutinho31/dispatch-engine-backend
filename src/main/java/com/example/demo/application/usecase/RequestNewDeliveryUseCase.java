package com.example.demo.application.usecase;

import com.example.demo.application.dto.DeliveryRequestDTO;
import com.example.demo.domain.Delivery;
import com.example.demo.domain.enums.DeliveryStatus;
import com.example.demo.repository.DeliveryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.DeliveryPricingService;
import com.example.demo.service.GoogleMapsService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class RequestNewDeliveryUseCase {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryPricingService pricingService;
    private final UserRepository userRepository;
    private final GoogleMapsService googleMapsService;

    public RequestNewDeliveryUseCase(DeliveryRepository deliveryRepository,
            DeliveryPricingService pricingService,
            UserRepository userRepository,
            GoogleMapsService googleMapsService) {
        this.deliveryRepository = deliveryRepository;
        this.pricingService = pricingService;
        this.userRepository = userRepository;
        this.googleMapsService = googleMapsService;
    }

    public Delivery execute(DeliveryRequestDTO dto) {

        double realDistanceKm = googleMapsService.getDistanceInKm(dto.pickupAddress(), dto.dropoffAddress());

        if (realDistanceKm == 0.0) {
            throw new RuntimeException("Não foi possível calcular uma rota terrestre entre os endereços informados.");
        }

        BigDecimal calculatedPrice = pricingService.calculate(dto.vehicleCategory(), realDistanceKm);

        var customer = userRepository.findById(dto.customerId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        double[] coordinates = googleMapsService.getCoordinatesFromAddress(dto.dropoffAddress());

        Delivery newDelivery = new Delivery();
        newDelivery.setCustomer(customer);
        newDelivery.setPickupAddress(dto.pickupAddress());
        newDelivery.setDropoffAddress(dto.dropoffAddress());
        newDelivery.setPrice(calculatedPrice);
        newDelivery.setStatus(DeliveryStatus.CRIADA);

        if (coordinates != null) {
            newDelivery.setDestinationLatitude(coordinates[0]);
            newDelivery.setDestinationLongitude(coordinates[1]);
        } else {
            System.out.println("Aviso: Coordenadas não encontradas para: " + dto.dropoffAddress());
            newDelivery.setDestinationLatitude(0.0);
            newDelivery.setDestinationLongitude(0.0);
        }

        return deliveryRepository.save(newDelivery);
    }
}
