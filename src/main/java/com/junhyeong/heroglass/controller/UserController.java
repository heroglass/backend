package com.junhyeong.heroglass.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("api/v1/signin")
    public SigninResponseDTO login(@RequestBody UserSigninDTO userSigninDTO) {
        return userService.signin(userSigninDTO);
    }

    @PostMapping("api/v1/signup")
    public TokenInfo signup(@RequestBody UserDataDTO userDataDTO) throws Exception {
        return userService.signup(userDataDTO);
    }


}
