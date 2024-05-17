package com.junhyeong.heroglass.repository.querydsl;

import com.junhyeong.heroglass.domain.item.Item;
import java.util.List;

public interface ItemRepositoryCustom {
    List<Item> findByCategory(String category);
}
