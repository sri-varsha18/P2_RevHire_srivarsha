package com.revhire.user.service;

import com.revhire.user.dto.*;

import java.util.List;

public interface UserService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

    com.revhire.user.entity.UserEntity getUserById(Integer id);

    List<com.revhire.user.entity.UserEntity> getAllUsers();

    void deleteUser(Integer id);
}