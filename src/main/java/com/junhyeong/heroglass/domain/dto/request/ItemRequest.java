package com.junhyeong.heroglass.domain.dto.request;

public record ItemRequest(String name, Integer price, Integer stockQuantity, String categoryName, String etc) {
}
