package com.junhyeong.heroglass.dto;

public record SignupRequest(String name, String email, String password, UserRole role) {
}
