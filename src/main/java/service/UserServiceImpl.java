package com.revhire.user.service;

import com.revhire.user.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    // 🔹 Register
    @Override
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        com.revhire.user.entity.UserEntity user = UserMapper.toEntity(request);
        user.setCreatedAt(LocalDateTime.now());

        userRepository.save(user);

        return AuthResponse.builder()
                .message("User registered successfully")
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    // 🔹 Login
    @Override
    public AuthResponse login(LoginRequest request) {

        com.revhire.user.entity.UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return AuthResponse.builder()
                .message("Login successful")
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    // 🔹 Get User By ID
    @Override
    public com.revhire.user.entity.UserEntity getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // 🔹 Get All Users
    @Override
    public List<com.revhire.user.entity.UserEntity> getAllUsers() {
        return userRepository.findAll();
    }

    // 🔹 Delete User
    @Override
    public void deleteUser(Integer id) {

        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        userRepository.deleteById(id);
    }
}