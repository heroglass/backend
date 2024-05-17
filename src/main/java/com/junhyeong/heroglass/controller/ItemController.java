package com.junhyeong.heroglass.controller;

import com.junhyeong.heroglass.domain.item.GlassesFrame;
import com.junhyeong.heroglass.dto.CategoryResponse;
import com.junhyeong.heroglass.dto.GlassesFrameRequest;
import com.junhyeong.heroglass.service.ItemService;
import java.net.http.HttpResponse;
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
    public ResponseEntity<String> createGlassesFrame(@RequestBody GlassesFrameRequest glassesFrameRequest) {
        System.out.println(glassesFrameRequest.name()+" " +glassesFrameRequest.price()+" " + glassesFrameRequest.stockQuantity());

        itemService.saveGlassesFrame(glassesFrameRequest);
        return ResponseEntity.ok("GlassesFrame saved successfully");
    }


}
