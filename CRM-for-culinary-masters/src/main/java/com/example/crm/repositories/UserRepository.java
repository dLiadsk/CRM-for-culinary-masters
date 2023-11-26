package com.example.crm.repositories;

import com.example.crm.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
   User findUserByUsername(String username);
   User findUserByUserId(Long id);
}
