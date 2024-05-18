package com.junhyeong.heroglass.repository;

import com.junhyeong.heroglass.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
