package com.example.bankingsystem.authentication;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;

public interface AuthenticationStrategy {

    boolean canHandle(AuthType authType);
    Authentication authenticate(HttpServletRequest request) throws Exception;
}
