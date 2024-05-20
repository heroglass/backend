package com.junhyeong.heroglass.domain.dto.response;

public record VerificationResponse(String status, String orderUuid, int payAmount, int orderAmount) {
}
