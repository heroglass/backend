package com.junhyeong.heroglass.dto;

public record OrderRequest(Long userId, Long itemId, int count) {
}
