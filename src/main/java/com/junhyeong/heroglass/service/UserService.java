package com.junhyeong.heroglass.service;

import com.junhyeong.heroglass.domain.Address;
import com.junhyeong.heroglass.domain.AppUser;
import com.junhyeong.heroglass.domain.Vision;
import com.junhyeong.heroglass.domain.dto.request.AddressRequest;
import com.junhyeong.heroglass.domain.dto.response.AddressResponse;
import com.junhyeong.heroglass.domain.dto.request.SigninRequest;
import com.junhyeong.heroglass.domain.dto.response.SigninResponse;
import com.junhyeong.heroglass.domain.dto.request.SignupRequest;
import com.junhyeong.heroglass.domain.dto.response.TokenResponse;
import com.junhyeong.heroglass.domain.dto.response.UserResponse;
import com.junhyeong.heroglass.domain.dto.request.VisionRequest;
import com.junhyeong.heroglass.domain.dto.response.VisionResponse;
import com.junhyeong.heroglass.exception.CustomException;
import com.junhyeong.heroglass.jwt.JwtTokenProvider;
import com.junhyeong.heroglass.repository.UserRepository;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.transaction.Transactional;
import java.security.Key;
import java.util.Collections;
import java.util.NoSuchElementException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
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

            findUser.updateToken(tokenResponse.tokenInfo().getAccessToken(),
                    tokenResponse.tokenInfo().getRefreshToken());

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

    public UserResponse findById(Long id) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id:" + id));

        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getAddress(),
                user.getUserRole(), user.getOrders());
    }

    @Transactional
    public VisionResponse updateVision(Long id, VisionRequest visionRequest) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id:" + id));
        user.updateVision(new Vision(visionRequest.leftEye(), visionRequest.rightEye()));

        return new VisionResponse(user.getId(), user.getUsername(), user.getVision());
    }

    @Transactional
    public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {
        AppUser user = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User not found with id:" + id));
        Address address = new Address(addressRequest.address(), addressRequest.detail());
        user.updateAddress(address);

        return new AddressResponse(user.getId(), user.getUsername(), address.getAddress(), address.getDetail());
    }

}

