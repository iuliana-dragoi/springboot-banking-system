package com.example.bankingsystem.user.repository;

import com.example.bankingsystem.user.model.User;
import com.example.bankingsystem.user.repository.projection.UserSearchProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Page<UserSearchProjection> findAllBy(Pageable pageable);

}
