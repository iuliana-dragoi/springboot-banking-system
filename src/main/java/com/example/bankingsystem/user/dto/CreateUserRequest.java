package com.example.bankingsystem.user.dto;

import com.example.bankingsystem.user.model.RoleType;
import com.example.bankingsystem.user.validation.StrongPassword;

public record CreateUserRequest(

        String username,
        String email,

        @StrongPassword
        String password,

        RoleType role
) {

}
