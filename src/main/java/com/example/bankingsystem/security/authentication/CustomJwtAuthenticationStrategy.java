package com.example.bankingsystem.security.authentication;

import com.example.bankingsystem.security.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomJwtAuthenticationStrategy implements AuthenticationStrategy {

    private final JwtService jwtService;

    public CustomJwtAuthenticationStrategy(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public boolean canHandle(AuthType authType) {
        return authType == AuthType.JWT_CUSTOM;
    }

    @Override
    public Authentication authenticate(HttpServletRequest request) throws Exception {
        String token = extractToken(request);
        String username = jwtService.extractUsername(token);
        UsernamePasswordAuthenticationToken authToken = null;

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            List<SimpleGrantedAuthority> authorities = jwtService.extractRoles(token);
            authToken = new UsernamePasswordAuthenticationToken(
                    username, null, authorities
            );
        }

        return authToken;
    }

    private String extractToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            return auth.substring(7);
        }
        throw new RuntimeException("No token found");
    }
}
