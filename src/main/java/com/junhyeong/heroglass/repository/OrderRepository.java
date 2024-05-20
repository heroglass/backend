package com.junhyeong.heroglass.repository;

import com.junhyeong.heroglass.domain.Order;
import java.util.Arrays;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findByOrderUuid(String orderUuid);

    List<Order> findByUserId(Long id);
}
