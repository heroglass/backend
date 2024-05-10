package com.junhyeong.heroglass.controller;

import com.junhyeong.heroglass.dto.SigninResponse;
import com.junhyeong.heroglass.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("api/v1/signin")
    public SigninResponse login(@RequestBody UserSigninDTO userSigninDTO) {
        return userService.signin(userSigninDTO);
    }

    @PostMapping("api/v1/signup")
    public TokenInfo signup(@RequestBody UserDataDTO userDataDTO) throws Exception {
        return userService.signup(userDataDTO);
    }


}
