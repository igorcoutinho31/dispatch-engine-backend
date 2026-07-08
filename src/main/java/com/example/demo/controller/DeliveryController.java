package com.example.demo.controller;

import com.example.demo.application.dto.DeliveryRequestDTO;
import com.example.demo.application.usecase.RequestNewDeliveryUseCase;
import com.example.demo.domain.Delivery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

    private final RequestNewDeliveryUseCase requestNewDeliveryUseCase;

    public DeliveryController(RequestNewDeliveryUseCase requestNewDeliveryUseCase) {
        this.requestNewDeliveryUseCase = requestNewDeliveryUseCase;
    }

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody DeliveryRequestDTO dto) {
        Delivery createdDelivery = requestNewDeliveryUseCase.execute(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDelivery);
    }
}