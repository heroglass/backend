package com.junhyeong.heroglass.repository.querydsl;

import com.junhyeong.heroglass.domain.Delivery;

public interface DeliveryRepositoryCustom {
    Delivery findDeliveryByOrderId(Long orderId);
}
