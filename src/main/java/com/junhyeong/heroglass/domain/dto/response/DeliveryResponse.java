package com.junhyeong.heroglass.domain.dto.response;

import com.junhyeong.heroglass.domain.DeliveryStatus;
import jakarta.annotation.Nullable;

public record DeliveryResponse(String address, String detail, DeliveryStatus status) {
}
