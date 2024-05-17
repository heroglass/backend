package com.junhyeong.heroglass.controller;

import com.junhyeong.heroglass.dto.ItemRequest;
import com.junhyeong.heroglass.dto.ItemResponse;
import com.junhyeong.heroglass.service.ItemService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/lens")
    public ResponseEntity<String> createLens(@RequestBody ItemRequest itemRequest) {
        itemService.saveLens(itemRequest);
        return ResponseEntity.ok("Lens saved successfully");
    }

    @PostMapping("/sunglasses")
    public ResponseEntity<String> createSunglasses(@RequestBody ItemRequest itemRequest) {
        itemService.saveSunglasses(itemRequest);
        return ResponseEntity.ok("Sunglasses saved successfully");
    }


    @GetMapping("/{category}")
    public ResponseEntity<List<ItemResponse>> getItemsWithCategory(@PathVariable("category") String category) {
        return ResponseEntity.ok(itemService.findByCategory(category));
    }


}
