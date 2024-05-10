package com.junhyeong.heroglass.controller;

import com.junhyeong.heroglass.domain.MilitaryGlassesShop;
import com.junhyeong.heroglass.service.MilitaryGlassesShopService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class MilitaryGlassesShopController {
    @Autowired
    private MilitaryGlassesShopService service;

    @PostMapping("/shops")
    public ResponseEntity<String> createShops(@RequestBody List<MilitaryGlassesShop> militaryGlassesShops) {
        service.saveAll(militaryGlassesShops);
        return ResponseEntity.ok("Data saved successfully");
    }
}
