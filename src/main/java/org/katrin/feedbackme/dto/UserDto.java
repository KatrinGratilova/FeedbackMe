package org.katrin.feedbackme.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private long id;
    private String email;
    private String phoneNumber;
    private String name;
    private String bio;
    private Set<String> roles;
    private Double avgRating;
    private List<Long> reviewsReceived;
    private List<Long> reviewsWritten;
}