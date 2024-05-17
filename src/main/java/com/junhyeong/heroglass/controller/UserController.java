package com.junhyeong.heroglass.controller;

import com.junhyeong.heroglass.dto.ItemResponse;
import com.junhyeong.heroglass.dto.SigninRequest;
import com.junhyeong.heroglass.dto.SigninResponse;
import com.junhyeong.heroglass.domain.TokenInfo;
import com.junhyeong.heroglass.dto.SignupRequest;
import com.junhyeong.heroglass.dto.TokenResponse;
import com.junhyeong.heroglass.dto.UserResponse;
import com.junhyeong.heroglass.exception.CustomException;
import com.junhyeong.heroglass.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/signin")
    public SigninResponse login(@RequestBody SigninRequest signinRequest) {
        return userService.signin(signinRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest signupRequest) throws Exception {
        try {
            userService.signup(signupRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
        }
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<UserResponse> getInfo(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userService.findById(id));
    }

}
