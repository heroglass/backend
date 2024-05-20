package com.junhyeong.heroglass.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.junhyeong.heroglass.domain.dto.request.OrderRequest;
import com.junhyeong.heroglass.domain.dto.response.OrderResponse;
import com.junhyeong.heroglass.domain.dto.request.VerificationRequest;
import com.junhyeong.heroglass.domain.dto.response.VerificationResponse;
import com.junhyeong.heroglass.service.OrderService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/{id}")
    public ResponseEntity<OrderResponse> createOrder(@PathVariable("id") Long id, @RequestBody OrderRequest orderRequest)
            throws JsonProcessingException {
        return ResponseEntity.ok(orderService.createOrder(id, orderRequest));
    }

    @PostMapping("/order/verification")
    public ResponseEntity<VerificationResponse> verification(@RequestBody VerificationRequest verificationRequest)
            throws JsonProcessingException {
        return ResponseEntity.ok(orderService.verification(verificationRequest));
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<List<OrderResponse>> getOrders(@PathVariable("id") Long id) {
        System.out.println(id);
        return ResponseEntity.ok(orderService.getOrders(id));
    }


}
