package org.katrin.feedbackme.dto;

import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateDto {
    private long id;
    private String email;
    private String phoneNumber;
    private String name;
    private String bio;
    private String password;
    private Set<String> roles;
}
