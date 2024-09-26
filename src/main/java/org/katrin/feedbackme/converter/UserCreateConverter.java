package org.katrin.feedbackme.converter;

import org.katrin.feedbackme.dto.UserCreateDto;
import org.katrin.feedbackme.entity.UserEntity;

public class UserCreateConverter {
    public static UserEntity toEntity(UserCreateDto user){
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .bio(user.getBio())
                .password(user.getPassword())
                .build();
    }

    public static UserCreateDto toDto(UserEntity user){
        return UserCreateDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .bio(user.getBio())
                .password(user.getPassword())
                .build();
    }
}
