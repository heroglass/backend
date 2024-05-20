package com.junhyeong.heroglass.repository.querydsl.impl;

import com.junhyeong.heroglass.domain.Delivery;
import com.junhyeong.heroglass.domain.QOrder;
import com.junhyeong.heroglass.repository.querydsl.DeliveryRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class DeliveryRepositoryImpl implements DeliveryRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Delivery findDeliveryByOrderId(Long orderId) {

        return queryFactory.select(QOrder.order.delivery)
                .from(QOrder.order)
                .where(QOrder.order.id.eq(orderId))
                .fetchOne();

    }

}
