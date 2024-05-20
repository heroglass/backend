package com.junhyeong.heroglass.repository;

import com.junhyeong.heroglass.domain.Delivery;
import com.junhyeong.heroglass.repository.querydsl.DeliveryRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>, DeliveryRepositoryCustom {
}
