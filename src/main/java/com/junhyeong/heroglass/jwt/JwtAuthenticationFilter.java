package com.junhyeong.heroglass.jwt;


import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;


    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            jakarta.servlet.FilterChain chain) throws jakarta.servlet.ServletException, IOException {

        // 로그인 및 회원가입 경로 정의
        String requestURI = request.getRequestURI();

        // 로그인과 회원가입 요청 시 JWT 검증을 건너뜁니다.
        if ("/api/v1/signin".equals(requestURI) || "/api/v1/signup".equals(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        Authentication authentication = jwtTokenProvider.getAuthentication(token);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter((jakarta.servlet.ServletRequest) request, (ServletResponse) response);
    }


}