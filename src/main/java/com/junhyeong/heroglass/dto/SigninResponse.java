package com.junhyeong.heroglass.dto;

import com.junhyeong.heroglass.domain.TokenInfo;

public record SigninResponse(Long userId, TokenInfo tokenInfo) {
}
