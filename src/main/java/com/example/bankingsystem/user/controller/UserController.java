package com.example.bankingsystem.user.controller;

import com.example.bankingsystem.user.dto.CreateUserRequest;
import com.example.bankingsystem.user.dto.CreateUserResponse;
import com.example.bankingsystem.user.repository.projection.UserSearchProjection;
import com.example.bankingsystem.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/search")
    public Page<UserSearchProjection> searchAll(Pageable pageable) {
        return userService.searchAll(pageable);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid CreateUserRequest request) {
        CreateUserResponse result = userService.create(request);
        return ResponseEntity.ok(result);
    }
}
