package com.example.bankingsystem.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class KeycloakAuthenticationStrategy implements AuthenticationStrategy {

    private final JwtDecoder jwtDecoder;

    public KeycloakAuthenticationStrategy(JwtDecoder jwtDecoder) {
        this.jwtDecoder = jwtDecoder;
    }

    @Override
    public boolean canHandle(AuthType authType) {
        return authType == AuthType.KEYCLOAK;
    }

    @Override
    public Authentication authenticate(HttpServletRequest request) throws Exception {
        String token = extractToken(request);
        Jwt jwt = jwtDecoder.decode(token);

        Map<String, Object> realmAccess = jwt.getClaimAsMap("realm_access");
        List<String> roles = realmAccess != null ? (List<String>) realmAccess.get("roles") : List.of();

        List<SimpleGrantedAuthority> authorities = roles.stream()
                .map(SimpleGrantedAuthority::new).toList();

        return new UsernamePasswordAuthenticationToken(
                jwt.getSubject(), null, authorities
        );

//        return new BearerTokenAuthenticationToken(token);
    }

    private String extractToken(HttpServletRequest request) {
        String auth = request.getHeader("Authorization");
        if (auth != null && auth.startsWith("Bearer ")) {
            return auth.substring(7);
        }
        throw new RuntimeException("No token found");
    }
}
