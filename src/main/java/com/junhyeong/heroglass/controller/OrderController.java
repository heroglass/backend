package com.junhyeong.heroglass.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.junhyeong.heroglass.dto.OrderRequest;
import com.junhyeong.heroglass.dto.PrepareOrderRequest;
import com.junhyeong.heroglass.dto.PrepareOrderResponse;
import com.junhyeong.heroglass.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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


    @PostMapping("/order/prepare/{id}")
    public ResponseEntity<PrepareOrderResponse> prepareOrder(@PathVariable("id") Long id,
                                                             @RequestBody PrepareOrderRequest prepareOrderRequest)
            throws JsonProcessingException {
        return ResponseEntity.ok(orderService.prepareOrder(id, prepareOrderRequest));
    }

    @PostMapping("/order/{id}")
    public void createOrder(@PathVariable("id") Long id, @RequestBody OrderRequest orderRequest) {
        orderService.createOrder();
    }


}
