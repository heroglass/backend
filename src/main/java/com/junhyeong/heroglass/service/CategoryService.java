package com.junhyeong.heroglass.service;

import com.junhyeong.heroglass.domain.Category;
import com.junhyeong.heroglass.domain.dto.request.CategoryRequest;
import com.junhyeong.heroglass.domain.dto.response.CategoryResponse;
import com.junhyeong.heroglass.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryResponse createCategory(CategoryRequest categoryRequest) {

        Category category = new Category();

        if (categoryRequest.parentId() != null) {
            Category parent = categoryRepository.findById(categoryRequest.parentId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid parent category ID"));
            category = new Category(categoryRequest.name(), parent);
            categoryRepository.save(category);
        }

        if (categoryRequest.parentId() == null) {
            category = new Category(categoryRequest.name(), null);
            categoryRepository.save(category);
        }

        return new CategoryResponse(category.getId(), category.getName());
    }


}
