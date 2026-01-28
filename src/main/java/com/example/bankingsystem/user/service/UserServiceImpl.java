package com.example.bankingsystem.user.service;

import com.example.bankingsystem.user.dto.CreateUserRequest;
import com.example.bankingsystem.user.dto.CreateUserResponse;
import com.example.bankingsystem.user.model.User;
import com.example.bankingsystem.user.repository.UserRepository;
import com.example.bankingsystem.user.repository.projection.UserSearchProjection;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserSearchProjection> searchAll(Pageable pageable) {
        return userRepository.findAllBy(pageable);
    }

    @Override
    public CreateUserResponse create(CreateUserRequest request) {

        User user = new User(
            request.username(),
            request.password(),
            request.email(),
            true,
            request.role()
        );

        userRepository.save(user);
        return new CreateUserResponse(true);
    }
}
