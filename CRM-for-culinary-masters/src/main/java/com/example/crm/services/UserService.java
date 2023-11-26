package com.example.crm.services;

import com.example.crm.models.User;
import com.example.crm.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;

    public User findUserById(long id) {
        return userRepository.findUserByUserId(id);
    }

    public boolean createUser(User user) {
        if (userRepository.findUserByUsername(user.getUsername()) != null) return false;
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
}
