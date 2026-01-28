package com.example.bankingsystem.user.service;

import com.example.bankingsystem.user.dto.CreateUserRequest;
import com.example.bankingsystem.user.dto.CreateUserResponse;
import com.example.bankingsystem.user.repository.projection.UserSearchProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserSearchProjection> searchAll(Pageable pageable);

    CreateUserResponse create(CreateUserRequest request);
}
