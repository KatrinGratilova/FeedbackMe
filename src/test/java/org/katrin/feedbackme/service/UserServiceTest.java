package org.katrin.feedbackme.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.katrin.feedbackme.converter.ReviewConverter;
import org.katrin.feedbackme.converter.UserConverter;
import org.katrin.feedbackme.converter.UserCreateConverter;
import org.katrin.feedbackme.dto.ReviewDto;
import org.katrin.feedbackme.dto.UserCreateDto;
import org.katrin.feedbackme.dto.UserDto;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.entity.Role;
import org.katrin.feedbackme.entity.UserEntity;
import org.katrin.feedbackme.repository.User.UserRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void saveTest_ok() {
        int id = 1;
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .id(id)
                .password(passwordEncoder.encode("1"))
                .roles(new HashSet<>(Set.of(Role.ROLE_USER.toString())))
                .build();
        UserEntity userEntity = UserCreateConverter.toEntity(userCreateDto);

        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntity);

        UserCreateDto actual = userService.save(userCreateDto);

        assertEquals(actual, userCreateDto);
        verify(userRepository, times(1)).save(any(UserEntity.class));
    }

    @Test
    void getByIdTest_ok() {
        long id = 1L;
        UserEntity userEntity = UserEntity.builder().id(id).roles(Set.of(Role.ROLE_USER)).build();
        UserDto userDto = UserConverter.toDto(userEntity);

        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));

        UserDto actual = userService.getById(id);

        assertEquals(userDto, actual);
        assertEquals(actual.getId(), userDto.getId());
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    public void getByIdTest_notFound() {
        long id = 1L;

        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.getById(id));
        verify(userRepository, times(1)).findById(id);
    }

    @Test
    void getByNameTest_ok() {
        String name = "Katrin";
        UserEntity userEntity = UserEntity.builder().id(1L).name(name).roles(Set.of(Role.ROLE_USER)).build();
        List<UserEntity> users = List.of(userEntity);

        when(userRepository.findByName(name)).thenReturn(users);

        List<UserDto> actual = userService.getByName(name);

        assertEquals(1, actual.size());
        assertEquals(name, actual.get(0).getName());
        verify(userRepository, times(1)).findByName(name);
    }

    @Test
    void getAllTest_ok() {
        UserEntity user1 = UserEntity.builder().id(1L).name("Katrin").roles(Set.of(Role.ROLE_USER)).build();
        UserEntity user2 = UserEntity.builder().id(2L).name("Vadim").roles(Set.of(Role.ROLE_USER)).build();

        List<UserEntity> users = List.of(user1, user2);
        List<UserDto> expectedDtos = List.of(UserConverter.toDto(user1), UserConverter.toDto(user2));

        when(userRepository.findAll()).thenReturn(users);

        List<UserDto> actualDtos = userService.getAll();

        assertEquals(expectedDtos.size(), actualDtos.size());
        assertEquals(expectedDtos, actualDtos);
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void addReviewTest_ok() {
        long userId = 1L;
        ReviewDto reviewDto = ReviewDto.builder().rating(5).build();
        ReviewEntity reviewEntity = ReviewConverter.toEntity(reviewDto);
        UserEntity userEntity = UserEntity.builder()
                .id(userId)
                .reviewsReceived(List.of(reviewEntity))
                .roles(Set.of(Role.ROLE_USER))
                .build();
        UserDto expected = UserConverter.toDto(userEntity);

        when(userRepository.addReview(userId, reviewEntity)).thenReturn(userEntity);

        UserDto actual = userService.addReview(userId, reviewDto);

        assertEquals(expected, actual);
        assertEquals(userId, actual.getId());
        verify(userRepository, times(1)).addReview(userId, reviewEntity);
    }

    @Test
    void updateWithoutReviewsTest_ok() {
        UserCreateDto userCreateDto = UserCreateDto.builder()
                .id(1L).name("Katrin")
                .password("1")
                .roles(new HashSet<>(Set.of(Role.ROLE_USER.toString())))
                .build();
        UserEntity userEntity = UserCreateConverter.toEntity(userCreateDto);

        when(userRepository.updateWithoutReviews(any(UserEntity.class))).thenReturn(userEntity);

        UserCreateDto actual = userService.updateWithoutReviews(userCreateDto);

        assertEquals(userCreateDto, actual);
        assertEquals("Katrin", actual.getName());
        verify(userRepository, times(1)).updateWithoutReviews(any(UserEntity.class));
    }

    @Test
    void deleteByIdTest_ok() {
        long id = 1L;

        doNothing().when(userRepository).deleteById(id);

        userService.deleteById(id);

        verify(userRepository, times(1)).deleteById(id);
    }
}
