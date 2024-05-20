package com.junhyeong.heroglass.domain.dto.response;

import com.junhyeong.heroglass.domain.TokenInfo;

public record SigninResponse(Long userId, TokenInfo tokenInfo) {
}
