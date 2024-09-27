package org.katrin.feedbackme.converter;

import org.junit.jupiter.api.Test;
import org.katrin.feedbackme.dto.ReviewDto;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.entity.UserEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReviewConverterTest {
    @Test
    public void toEntityTest_Ok() {
        ReviewDto reviewDto = ReviewDto.builder()
                .id(1)
                .comment("Very nice user!")
                .rating(4)
                .authorId(1)
                .recipientId(2)
                .build();

        ReviewEntity reviewEntity = ReviewConverter.toEntity(reviewDto);

        assertEquals(reviewEntity.getId(), reviewDto.getId());
        assertEquals(reviewEntity.getComment(), reviewDto.getComment());
        assertEquals(reviewEntity.getRating(), reviewDto.getRating());
        assertEquals(reviewEntity.getAuthor().getId(), reviewDto.getAuthorId());
        assertEquals(reviewEntity.getRecipient().getId(), reviewDto.getRecipientId());
    }

    @Test
    public void toDtoTest_Ok() {
        ReviewEntity reviewEntity = ReviewEntity.builder()
                .id(1)
                .comment("Very nice user!")
                .rating(4)
                .author(UserEntity.builder().id(1).build())
                .recipient(UserEntity.builder().id(2).build())
                .build();

        ReviewDto reviewDto = ReviewConverter.toDto(reviewEntity);

        assertEquals(reviewDto.getId(), reviewEntity.getId());
        assertEquals(reviewDto.getComment(), reviewEntity.getComment());
        assertEquals(reviewDto.getRating(), reviewEntity.getRating());
        assertEquals(reviewDto.getAuthorId(), reviewEntity.getAuthor().getId());
        assertEquals(reviewDto.getRecipientId(), reviewEntity.getRecipient().getId());
    }
}
