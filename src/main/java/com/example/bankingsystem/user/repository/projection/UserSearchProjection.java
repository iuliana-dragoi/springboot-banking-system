package com.example.bankingsystem.user.repository.projection;

import com.example.bankingsystem.user.model.RoleType;

public interface UserSearchProjection {

    Long getId();
    String getFirstName();
    String getLastName();
    String getEmail();
    RoleType getRoleType();
    boolean isActive();
}
