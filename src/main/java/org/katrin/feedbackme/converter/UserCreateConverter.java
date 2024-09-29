package org.katrin.feedbackme.converter;

import org.katrin.feedbackme.dto.UserCreateDto;
import org.katrin.feedbackme.entity.Role;
import org.katrin.feedbackme.entity.UserEntity;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UserCreateConverter {
    public static UserEntity toEntity(UserCreateDto user) {
        Set<String> roles = Optional.ofNullable(user.getRoles()).orElse(new HashSet<>());
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .bio(user.getBio())
                .password(user.getPassword())
                .roles(roles.stream().map(Role::valueOf).collect(Collectors.toSet()))
                .build();
    }

    public static UserCreateDto toDto(UserEntity user) {
        return UserCreateDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .bio(user.getBio())
                .password(user.getPassword())
                .roles(user.getRoles().stream().map(Role::toString).collect(Collectors.toSet()))
                .build();
    }
}
