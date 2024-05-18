package com.junhyeong.heroglass.dto;

public record VerificationResponse(String status, String orderUuid, int payAmount, int orderAmount) {
}
