package com.junhyeong.heroglass.repository.querydsl.impl;

import static com.junhyeong.heroglass.domain.QCategoryItem.*;
import static com.junhyeong.heroglass.domain.item.QItem.*;

import com.junhyeong.heroglass.domain.CategoryItem;
import com.junhyeong.heroglass.domain.QCategoryItem;
import com.junhyeong.heroglass.domain.item.Item;
import com.junhyeong.heroglass.domain.item.QItem;
import com.junhyeong.heroglass.dto.ItemResponse;
import com.junhyeong.heroglass.repository.querydsl.ItemRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<Item> findByCategory(String category) {

        return queryFactory.selectFrom(item)
                .innerJoin(categoryItem).on(item.id.eq(categoryItem.item.id))
                .innerJoin(categoryItem.category).on(categoryItem.category.name.eq(category))
                .fetch();


    }
}
