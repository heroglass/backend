package com.junhyeong.heroglass.controller;

import com.junhyeong.heroglass.domain.dto.response.DeliveryResponse;
import com.junhyeong.heroglass.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/delivery/{id}")
    public ResponseEntity<DeliveryResponse> getDelivery(@PathVariable("id") Long id) {
        return ResponseEntity.ok(deliveryService.getDelivery(id));
    }
}
