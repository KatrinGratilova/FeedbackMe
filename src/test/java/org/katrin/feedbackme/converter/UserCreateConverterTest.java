package org.katrin.feedbackme.converter;

import org.junit.jupiter.api.Test;
import org.katrin.feedbackme.dto.UserCreateDto;
import org.katrin.feedbackme.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserCreateConverterTest {
    @Test
    public void toEntityTest_Ok() {
        UserCreateDto userDto = UserCreateDto.builder()
                .id(1)
                .email("one@gmail.com")
                .phoneNumber("+123456789")
                .name("Katrin")
                .bio("Student")
                .password("1234")
                .build();

        UserEntity userEntity = UserCreateConverter.toEntity(userDto);

        assertEquals(userEntity.getId(), userDto.getId());
        assertEquals(userEntity.getEmail(), userDto.getEmail());
        assertEquals(userEntity.getPhoneNumber(), userDto.getPhoneNumber());
        assertEquals(userEntity.getName(), userDto.getName());
        assertEquals(userEntity.getBio(), userDto.getBio());
        assertEquals(userEntity.getPassword(), userDto.getPassword());
    }

    @Test
    public void toDtoTest_Ok() {
        UserEntity userEntity = UserEntity.builder()
                .id(1)
                .email("one@gmail.com")
                .phoneNumber("+123456789")
                .name("Katrin")
                .bio("Student")
                .password("1234")
                .build();

        UserCreateDto userCreateDto = UserCreateConverter.toDto(userEntity);
        assertEquals(userCreateDto.getId(), userEntity.getId());
        assertEquals(userCreateDto.getEmail(), userEntity.getEmail());
        assertEquals(userCreateDto.getPhoneNumber(), userEntity.getPhoneNumber());
        assertEquals(userCreateDto.getName(), userEntity.getName());
        assertEquals(userCreateDto.getBio(), userEntity.getBio());
        assertEquals(userCreateDto.getPassword(), userEntity.getPassword());
    }
}
