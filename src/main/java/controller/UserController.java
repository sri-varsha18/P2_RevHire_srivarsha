package com.revhire.user.controller;

import com.revhire.user.dto.*;
import com.revhire.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 🔹 Register User
    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }

    // 🔹 Login User
    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    // 🔹 Get User By ID
    @GetMapping("/{id}")
    public com.revhire.user.entity.UserEntity getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    // 🔹 Get All Users
    @GetMapping
    public List<com.revhire.user.entity.UserEntity> getAllUsers() {
        return userService.getAllUsers();
    }

    // 🔹 Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return "User deleted successfully";
    }
}