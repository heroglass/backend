package com.junhyeong.heroglass.controller;

import com.junhyeong.heroglass.dto.ShopRequest;
import com.junhyeong.heroglass.dto.ShopResponse;
import com.junhyeong.heroglass.service.MilitaryGlassesShopService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MilitaryGlassesShopController {
    @Autowired
    private MilitaryGlassesShopService service;

    @PostMapping("/api/v1/shops")
    public ResponseEntity<String> createShops(@RequestBody List<ShopRequest> shopRequests) {
        service.saveAll(shopRequests);
        return ResponseEntity.ok("Data saved successfully");
    }

    @GetMapping("/api/v1/shops")
    public List<ShopResponse> findAllShops() {
        return service.findAllShops();
    }
}
