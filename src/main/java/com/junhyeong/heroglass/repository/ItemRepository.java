package com.junhyeong.heroglass.repository;

import com.junhyeong.heroglass.domain.item.Item;
import com.junhyeong.heroglass.repository.querydsl.ItemRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
}
