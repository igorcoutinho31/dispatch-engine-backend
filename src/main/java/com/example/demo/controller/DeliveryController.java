package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.controller.dto.DeliveryRequestDTO;
import com.example.demo.domain.Delivery;
import com.example.demo.service.DeliveryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/deliveries")
@AllArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody DeliveryRequestDTO dto) {

        Delivery savedDelivery = deliveryService.requestNewDelivery(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDelivery);
    }
}
