package com.junhyeong.heroglass.repository.querydsl.impl;

import com.junhyeong.heroglass.dto.CategoryRequest;
import com.junhyeong.heroglass.dto.CategoryResponse;
import com.junhyeong.heroglass.repository.querydsl.CategoryRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

}
