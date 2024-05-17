package com.junhyeong.heroglass.service;

import com.junhyeong.heroglass.domain.Category;
import com.junhyeong.heroglass.domain.CategoryItem;
import com.junhyeong.heroglass.domain.item.GlassesFrame;
import com.junhyeong.heroglass.domain.item.Item;
import com.junhyeong.heroglass.dto.GlassesFrameRequest;
import com.junhyeong.heroglass.repository.CategoryItemRepository;
import com.junhyeong.heroglass.repository.CategoryRepository;
import com.junhyeong.heroglass.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final CategoryRepository categoryRepository;

    private final ItemRepository itemRepository;

    public void saveGlassesFrame(GlassesFrameRequest glassesFrameRequest) {

        GlassesFrame glassesFrame = new GlassesFrame();
        Category category = categoryRepository.findByName(glassesFrameRequest.categoryName());

        glassesFrame.setName(glassesFrameRequest.name());
        glassesFrame.setPrice(glassesFrameRequest.price());
        glassesFrame.setStockQuantity(glassesFrameRequest.stockQuantity());
        CategoryItem categoryItem = new CategoryItem(category, glassesFrame);
        glassesFrame.getCategoryItems().add(categoryItem);

        itemRepository.save(glassesFrame);
    }
}
