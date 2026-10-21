package com.dev.resolveja.dto.mapper;

import com.dev.resolveja.domain.User;
import com.dev.resolveja.domain.enums.Role;
import com.dev.resolveja.dto.request.RegisterRequest;
import com.dev.resolveja.dto.response.RegisterResponse;

public class UserMapper {

    public static User toEntity(RegisterRequest request, String hashedPassword) {
        return User.builder()
            .name(request.name())
            .email(request.email())
            .password(hashedPassword)
            .birthDate(request.birthDate())
            .active(true)
            .role(Role.valueOf(request.role()))
        .build();
    }

    public static RegisterResponse toResponse(User user) {
        return RegisterResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .imageUrl(user.getImageUrl())
                .birthDate(user.getBirthDate())
                .active(user.getActive())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
            .build();
    }
}
