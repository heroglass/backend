package com.junhyeong.heroglass.repository;

import com.junhyeong.heroglass.domain.CategoryItem;
import com.junhyeong.heroglass.repository.querydsl.CategoryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem, Long>, CategoryRepositoryCustom {
}
