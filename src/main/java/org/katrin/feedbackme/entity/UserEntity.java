package org.katrin.feedbackme.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;

    @NotNull
    @Column(nullable = false)
    private String name;

    @Column(length = 300)
    private String bio;

    @NotNull
    @Column(nullable = false, length = 100)
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles = new HashSet<>();

    @Column(name = "average_rate")
    private Double avgRating = 0.0;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "recipient")
    private List<ReviewEntity> reviewsReceived = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "author")
    private List<ReviewEntity> reviewsWritten = new ArrayList<>();

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    public void recalculateAvgRating() {
        double sum = reviewsReceived.stream().mapToInt(ReviewEntity::getRating).sum();
        this.avgRating = reviewsReceived.isEmpty() ? 0 : sum / (double) reviewsReceived.size();
    }
}
