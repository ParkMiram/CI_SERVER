package com.example.ci.service;

import com.example.ci.domain.entity.User;
import com.example.ci.domain.repository.UserRepository;
import com.example.ci.dto.request.UserRequest;
import com.example.ci.dto.response.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public List<UserResponse> getUsers() {
        List<User> all = userRepository.findAll();
        return all.stream().map(UserResponse::from).toList();
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return UserResponse.from(user.orElseThrow(IllegalArgumentException::new));
    }

    @Transactional
    @Override
    public void insertUser(UserRequest request) {
        userRepository.save(request.toEntity());
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}