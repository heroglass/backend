package com.junhyeong.heroglass.dto;

import java.util.UUID;

public record OrderResponse(String orderUUID, String name, int price, int count) {
}
