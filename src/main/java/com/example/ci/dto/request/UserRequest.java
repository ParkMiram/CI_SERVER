package com.example.ci.dto.request;

import com.example.ci.domain.entity.User;

public record UserRequest(
        Long id,
        String name,
        int age
) {
    public User toEntity() {
        return User.builder()
                .name(name)
                .age(age)
                .build();
    }
}
