package org.katrin.feedbackme.service;

import lombok.RequiredArgsConstructor;
import org.katrin.feedbackme.converter.ReviewConverter;
import org.katrin.feedbackme.converter.UserConverter;
import org.katrin.feedbackme.converter.UserCreateConverter;
import org.katrin.feedbackme.dto.ReviewDto;
import org.katrin.feedbackme.dto.UserCreateDto;
import org.katrin.feedbackme.dto.UserDto;
import org.katrin.feedbackme.entity.Role;
import org.katrin.feedbackme.entity.UserEntity;
import org.katrin.feedbackme.repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    public UserCreateDto save(UserCreateDto userDto) {
        UserEntity userEntity = UserCreateConverter.toEntity(userDto);
        userEntity.setActive(true);
        //userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        userEntity.setRoles(new HashSet<>());
        userEntity.getRoles().add(Role.ROLE_USER);
        UserEntity saved = userRepository.save(userEntity);

        return UserCreateConverter.toDto(saved);
    }

    public UserDto getById(Long id) {
        return userRepository.findById(id).map(UserConverter::toDto).orElseThrow();
    }

    public List<UserDto> getByName(String name) {
        return userRepository.findByName(name).stream().map(UserConverter::toDto).toList();
    }

    public List<UserDto> getAll() {
        return userRepository.findAll().stream().map(UserConverter::toDto).toList();
    }

    public UserDto addReview(long id, ReviewDto reviewDto) {
        UserEntity userEntity = userRepository.addReview(id, ReviewConverter.toEntity(reviewDto));
        return UserConverter.toDto(userEntity);
    }

    public UserDto updateWithoutReviews(UserCreateDto userCreateDto) {
        UserEntity userEntity = userRepository.updateWithoutReviews(UserCreateConverter.toEntity(userCreateDto));
        return UserConverter.toDto(userEntity);
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
