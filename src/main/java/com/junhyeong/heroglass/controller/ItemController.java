package com.junhyeong.heroglass.controller;

import com.junhyeong.heroglass.dto.ItemRequest;
import com.junhyeong.heroglass.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @PostMapping("/glassesFrame")
    public ResponseEntity<String> createGlassesFrame(@RequestBody ItemRequest itemRequest) {

        itemService.saveGlassesFrame(itemRequest);
        return ResponseEntity.ok("GlassesFrame saved successfully");
    }


    @PostMapping("/glasses")
    public ResponseEntity<String> createGlasses(@RequestBody ItemRequest itemRequest) {

        itemService.saveGlasses(itemRequest);
        return ResponseEntity.ok("Glasses saved successfully");
    }


}
