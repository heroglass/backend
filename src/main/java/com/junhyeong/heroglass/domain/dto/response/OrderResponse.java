package com.junhyeong.heroglass.domain.dto.response;

import java.util.List;

public record OrderResponse(String orderUUID, List<OrderItemResponse> orderItems, int count, int price) {
}
