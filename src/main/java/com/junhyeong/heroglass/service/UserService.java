package com.junhyeong.heroglass.service;

import com.junhyeong.heroglass.domain.AppUser;
import com.junhyeong.heroglass.domain.TokenInfo;
import com.junhyeong.heroglass.dto.SigninRequest;
import com.junhyeong.heroglass.dto.SigninResponse;
import com.junhyeong.heroglass.dto.SignupRequest;
import com.junhyeong.heroglass.dto.TokenResponse;
import com.junhyeong.heroglass.exception.CustomException;
import com.junhyeong.heroglass.jwt.JwtTokenProvider;
import com.junhyeong.heroglass.repository.UserRepository;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import java.security.Key;
import java.util.Collections;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private UserRepository userRepository;

    private Key secretKey;

    public UserService(@Value("${jwt.secretKey}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.secretKey = Keys.hmacShaKeyFor(keyBytes);
    }


    @Transactional
    public SigninResponse signin(SigninRequest signinRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(signinRequest.email(), signinRequest.password()));

            AppUser findUser = userRepository.findByEmail(signinRequest.email());

            TokenResponse tokenResponse = jwtTokenProvider.generateToken(signinRequest.email(),
                    Collections.singleton(findUser.getUserRole()));
            log.info(findUser.getEmail());
            log.info(findUser.getPassword());

            findUser.update(tokenResponse.tokenInfo().getAccessToken(), tokenResponse.tokenInfo().getRefreshToken());

            return new SigninResponse(findUser.getId(), tokenResponse.tokenInfo());


        } catch (AuthenticationException e) {
            throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public AppUser signup(SignupRequest signupRequest) throws Exception {

        if (!userRepository.existsByEmail(signupRequest.email())) {
            AppUser user = new AppUser(signupRequest.name(), signupRequest.email(),
                    passwordEncoder.encode(signupRequest.password()), signupRequest.role());
            return userRepository.save(user);
        } else {
            throw new CustomException("useremail is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public AppUser loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}

