package com.example.bankingsystem.authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class DynamicAuthenticationFilter extends OncePerRequestFilter {

    private final List<AuthenticationStrategy> strategies;

    public DynamicAuthenticationFilter(List<AuthenticationStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        AuthType authType = extractAuthType(request);
        if(authType == null) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("X-Auth-Type not specified or invalid!");
            response.getWriter().flush();
            return;
        }

        AuthenticationStrategy strategy = strategies.stream()
                .filter(s -> s.canHandle(authType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No strategy for: " + authType));

        try {
            Authentication authentication = strategy.authenticate(request);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        filterChain.doFilter(request, response);
    }

    private AuthType extractAuthType(HttpServletRequest request) {
        String authType = request.getHeader("X-Auth-Type");
        return authType != null ? AuthType.valueOf(authType.toUpperCase()) : null;
    }
}