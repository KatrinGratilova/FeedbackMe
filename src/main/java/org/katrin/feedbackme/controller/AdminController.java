package org.katrin.feedbackme.controller;

import lombok.RequiredArgsConstructor;
import org.katrin.feedbackme.dto.UserCreateDto;
import org.katrin.feedbackme.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin-panel")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminController {
    private final UserService userService;

    @PutMapping("/users/{id}")
    public UserCreateDto assignAdminRole(@PathVariable long id) {
        return userService.assignAdminRole(id);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteById(id);
    }
}
