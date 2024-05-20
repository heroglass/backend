package com.junhyeong.heroglass.domain.dto.response;

import com.junhyeong.heroglass.domain.Delivery;
import java.util.List;

public record OrderResponse(String orderUUID, List<OrderItemResponse> orderItems, int count, int price) {
}
