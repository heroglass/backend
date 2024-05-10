package com.junhyeong.heroglass.dto;

import com.junhyeong.heroglass.entity.TokenInfo;

public record SigninResponse(Long userId, TokenInfo tokenInfo) {
}
