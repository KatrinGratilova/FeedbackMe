package org.katrin.feedbackme.entity;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Entity
@Table(name = "reviews")
public class ReviewEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id", nullable = false)
    private long id;

    @NotNull
    @Column(nullable = false, length = 300)
    private String comment;

    @NotNull
    @Min(1)
    @Max(5)
    @Column(columnDefinition = "SMALLINT CHECK (rating BETWEEN 1 AND 5)", nullable = false)
    private int rating;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private UserEntity author;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", nullable = false)
    private UserEntity recipient;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
