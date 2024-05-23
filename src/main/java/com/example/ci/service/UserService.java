package com.example.ci.service;

import com.example.ci.dto.request.UserRequest;
import com.example.ci.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();

    UserResponse getUserById(Long id);

    void insertUser(UserRequest request);

    void deleteById(Long id);
}
