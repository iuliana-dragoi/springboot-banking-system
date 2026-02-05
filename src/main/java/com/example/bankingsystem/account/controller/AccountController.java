package com.example.bankingsystem.account.controller;

import com.example.bankingsystem.account.service.AccountService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
@Validated
public class AccountController {

    public final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/test")
    public String test() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        boolean hasAdmin = auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
        return "User: " + auth.getName() + " | Are ADMIN? " + hasAdmin;
    }
}
