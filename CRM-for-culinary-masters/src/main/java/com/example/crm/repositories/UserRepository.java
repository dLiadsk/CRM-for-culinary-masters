package com.example.crm.repositories;

import com.example.crm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
   User findUserByUsername(String username);
   User findUserByUserId(Long id);
   Optional<User> findUserByEmail(String email);
}
