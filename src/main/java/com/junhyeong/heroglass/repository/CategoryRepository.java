package com.junhyeong.heroglass.repository;

import com.junhyeong.heroglass.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
