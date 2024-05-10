package com.junhyeong.heroglass.dto;

import com.junhyeong.heroglass.domain.UserRole;

public record SignupRequest(String name, String email, String password, UserRole role) {
}
