package com.junhyeong.heroglass.service;

import com.junhyeong.heroglass.domain.Category;
import com.junhyeong.heroglass.domain.CategoryItem;
import com.junhyeong.heroglass.domain.item.Glasses;
import com.junhyeong.heroglass.domain.item.GlassesFrame;
import com.junhyeong.heroglass.domain.item.Lens;
import com.junhyeong.heroglass.domain.item.Sunglasses;
import com.junhyeong.heroglass.dto.ItemRequest;
import com.junhyeong.heroglass.repository.CategoryRepository;
import com.junhyeong.heroglass.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final CategoryRepository categoryRepository;

    private final ItemRepository itemRepository;

    public void saveGlassesFrame(ItemRequest glassesFrameRequest) {

        GlassesFrame glassesFrame = new GlassesFrame();
        Category category = categoryRepository.findByName(glassesFrameRequest.categoryName());

        glassesFrame.setName(glassesFrameRequest.name());
        glassesFrame.setPrice(glassesFrameRequest.price());
        glassesFrame.setStockQuantity(glassesFrameRequest.stockQuantity());
        CategoryItem categoryItem = new CategoryItem(category, glassesFrame);
        glassesFrame.getCategoryItems().add(categoryItem);

        itemRepository.save(glassesFrame);
    }

    public void saveGlasses(ItemRequest itemRequest) {

        Glasses glasses = new Glasses();
        Category category = categoryRepository.findByName(itemRequest.categoryName());

        glasses.setName(itemRequest.name());
        glasses.setPrice(itemRequest.price());
        glasses.setStockQuantity(itemRequest.stockQuantity());
        CategoryItem categoryItem = new CategoryItem(category, glasses);
        glasses.getCategoryItems().add(categoryItem);

        itemRepository.save(glasses);
    }


    public void saveLens(ItemRequest itemRequest) {
        Lens lens = new Lens();
        Category category = categoryRepository.findByName(itemRequest.categoryName());

        lens.setName(itemRequest.name());
        lens.setPrice(itemRequest.price());
        lens.setStockQuantity(itemRequest.stockQuantity());
        CategoryItem categoryItem = new CategoryItem(category, lens);
        lens.getCategoryItems().add(categoryItem);

        itemRepository.save(lens);
    }

    public void saveSunglasses(ItemRequest itemRequest) {
        Sunglasses sunglasses = new Sunglasses();
        Category category = categoryRepository.findByName(itemRequest.categoryName());

        sunglasses.setName(itemRequest.name());
        sunglasses.setPrice(itemRequest.price());
        sunglasses.setStockQuantity(itemRequest.stockQuantity());
        CategoryItem categoryItem = new CategoryItem(category, sunglasses);
        sunglasses.getCategoryItems().add(categoryItem);

        itemRepository.save(sunglasses);
    }
}
