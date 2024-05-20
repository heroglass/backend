package com.junhyeong.heroglass.domain.dto.request;

import com.junhyeong.heroglass.domain.UserRole;

public record SignupRequest(String name, String email, String password, UserRole role) {
}
