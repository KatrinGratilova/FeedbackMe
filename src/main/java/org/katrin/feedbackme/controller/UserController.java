package org.katrin.feedbackme.controller;

import lombok.RequiredArgsConstructor;
import org.katrin.feedbackme.dto.ReviewDto;
import org.katrin.feedbackme.dto.UserCreateDto;
import org.katrin.feedbackme.dto.UserDto;
import org.katrin.feedbackme.service.ReviewService;
import org.katrin.feedbackme.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ReviewService reviewService;

    @PostMapping
    public UserCreateDto save(@RequestBody UserCreateDto userCreateDto) {
        return userService.save(userCreateDto);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping(value = "/users", params = "/name")
    public List<UserDto> getByName(@RequestParam String name) {
        return userService.getByName(name);
    }

    @GetMapping
    public List<UserDto> getAll() {
        return userService.getAll();
    }

    @PostMapping("/{id}/reviews-received")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    public UserDto addReview(@PathVariable long id, @RequestBody ReviewDto reviewDto) {
        return userService.addReview(id, reviewDto);
    }

    @GetMapping("/{id}/reviews-received")
    public List<ReviewDto> getReviewsReceived(@PathVariable long id) {
        return reviewService.getByRecipientId(id);
    }

    @GetMapping("/{id}/reviews-written")
    public List<ReviewDto> getReviewsWritten(@PathVariable long id) {
        return reviewService.getByAuthorId(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping
    public UserCreateDto updateWithoutReviews(@RequestBody UserCreateDto userCreateDto) {
        return userService.updateWithoutReviews(userCreateDto);
    }
}
