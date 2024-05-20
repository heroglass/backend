package com.junhyeong.heroglass.service;

import com.junhyeong.heroglass.domain.Delivery;
import com.junhyeong.heroglass.domain.dto.response.DeliveryResponse;
import com.junhyeong.heroglass.repository.DeliveryRepository;
import com.junhyeong.heroglass.repository.OrderRepository;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryResponse getDelivery(Long id) {

        Delivery delivery = deliveryRepository.findDeliveryByOrderId(id);
        return new DeliveryResponse(
                delivery.getAddress().getAddress(),
                delivery.getAddress().getDetail(),
                delivery.getStatus());
    }
}
