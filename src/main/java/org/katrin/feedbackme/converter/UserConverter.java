package org.katrin.feedbackme.converter;

import org.katrin.feedbackme.dto.UserDto;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.entity.Role;
import org.katrin.feedbackme.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UserConverter {
    public static UserEntity toEntity(UserDto user){
        Set<Role> roles = user.getRoles().stream().map(Role::valueOf).collect(Collectors.toSet());
        List<Long> reviewsReceivedDtos = Optional.ofNullable(user.getReviewsReceived()).orElse(new ArrayList<>());
        List<ReviewEntity> reviewsReceivedEntities = reviewsReceivedDtos.stream().map(id -> ReviewEntity.builder().id(id).build()).toList();
        List<Long> reviewsWrittenDtos = Optional.ofNullable(user.getReviewsWritten()).orElse(new ArrayList<>());
        List<ReviewEntity> reviewsWrittenEntities = reviewsWrittenDtos.stream().map(id -> ReviewEntity.builder().id(id).build()).toList();
        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .bio(user.getBio())
                .roles(roles)
                .avgRating(user.getAvgRating())
                .reviewsReceived(reviewsReceivedEntities)
                .reviewsWritten(reviewsWrittenEntities)
                .build();
    }

    public static UserDto toDto(UserEntity user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName())
                .bio(user.getBio())
                .roles(user.getRoles().stream().map(Role::toString).collect(Collectors.toSet()))
                .avgRating(user.getAvgRating())
                .reviewsReceived(user.getReviewsReceived().stream().map(ReviewEntity::getId).toList())
                .reviewsWritten(user.getReviewsWritten().stream().map(ReviewEntity::getId).toList())
                .build();
    }
}
