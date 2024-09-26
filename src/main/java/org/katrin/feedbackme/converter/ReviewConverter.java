package org.katrin.feedbackme.converter;

import org.katrin.feedbackme.dto.ReviewDto;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.entity.UserEntity;

public class ReviewConverter {
    public static ReviewEntity toEntity(ReviewDto review){
        return ReviewEntity.builder()
                .id(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .author(UserEntity.builder().id(review.getAuthorId()).build())
                .recipient(UserEntity.builder().id(review.getRecipientId()).build())
                .build();
    }

    public static ReviewDto toDto(ReviewEntity review){
        return ReviewDto.builder()
                .id(review.getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .authorId(review.getAuthor().getId())
                .recipientId(review.getRecipient().getId())
                .build();
    }
}
