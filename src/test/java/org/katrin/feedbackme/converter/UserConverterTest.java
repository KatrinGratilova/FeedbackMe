package org.katrin.feedbackme.converter;

import org.junit.jupiter.api.Test;
import org.katrin.feedbackme.dto.UserDto;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.entity.Role;
import org.katrin.feedbackme.entity.UserEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserConverterTest {
    @Test
    public void toEntityTest_Ok() {
        UserDto userDto = UserDto.builder().id(1).email("one@gmail.com").phoneNumber("+123456789").name("Katrin").bio("Student").roles(new HashSet<>(Set.of(Role.ROLE_USER.toString()))).avgRating(4.5).reviewsReceived(List.of((long) 1, (long) 2)).reviewsWritten(List.of((long) 3, (long) 4)).build();

        UserEntity userEntity = UserConverter.toEntity(userDto);

        assertEquals(userEntity.getId(), userDto.getId());
        assertEquals(userEntity.getEmail(), userDto.getEmail());
        assertEquals(userEntity.getPhoneNumber(), userDto.getPhoneNumber());
        assertEquals(userEntity.getName(), userDto.getName());
        assertEquals(userEntity.getBio(), userDto.getBio());
        assertEquals(userEntity.getRoles().stream().map(Role::toString).collect(Collectors.toSet()), userDto.getRoles());
        assertEquals(userEntity.getAvgRating(), userDto.getAvgRating());
        assertEquals(userEntity.getReviewsReceived().stream().map(ReviewEntity::getId).toList(), userDto.getReviewsReceived());
        assertEquals(userEntity.getReviewsWritten().stream().map(ReviewEntity::getId).toList(), userDto.getReviewsWritten());
    }

    @Test
    public void toDtoTest_Ok() {
        UserEntity userEntity = UserEntity.builder().id(1).email("one@gmail.com").phoneNumber("+123456789").name("Katrin").bio("Student").roles(new HashSet<>(Set.of(Role.ROLE_USER))).avgRating(4.5).reviewsReceived(List.of(ReviewEntity.builder().id(1).build(), ReviewEntity.builder().id(2).build())).reviewsWritten(List.of(ReviewEntity.builder().id(3).build(), ReviewEntity.builder().id(4).build())).build();

        UserDto userDto = UserConverter.toDto(userEntity);
        assertEquals(userDto.getId(), userEntity.getId());
        assertEquals(userDto.getEmail(), userEntity.getEmail());
        assertEquals(userDto.getPhoneNumber(), userEntity.getPhoneNumber());
        assertEquals(userDto.getName(), userEntity.getName());
        assertEquals(userDto.getBio(), userEntity.getBio());
        assertEquals(userDto.getRoles(), userEntity.getRoles().stream().map(Role::toString).collect(Collectors.toSet()));
        assertEquals(userDto.getAvgRating(), userEntity.getAvgRating());
        assertEquals(userDto.getReviewsReceived(), userEntity.getReviewsReceived().stream().map(ReviewEntity::getId).toList());
        assertEquals(userDto.getReviewsWritten(), userEntity.getReviewsWritten().stream().map(ReviewEntity::getId).toList());
    }
}
