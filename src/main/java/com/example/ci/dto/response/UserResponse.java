package com.example.ci.dto.response;

import com.example.ci.domain.entity.User;

public record UserResponse(
        Long id,
        String name,
        int age
) {
    public static UserResponse from(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getAge()
        );
    }
}
