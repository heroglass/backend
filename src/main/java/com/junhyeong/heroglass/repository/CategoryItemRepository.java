package com.junhyeong.heroglass.repository;

import com.junhyeong.heroglass.domain.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long> {
}
