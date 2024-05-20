package com.junhyeong.heroglass.domain.dto.response;

public record OrderItemResponse(Long id, String name, int stockQuantity, int price, int count) {
}
