package org.katrin.feedbackme.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.katrin.feedbackme.converter.ReviewConverter;
import org.katrin.feedbackme.dto.ReviewDto;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.entity.UserEntity;
import org.katrin.feedbackme.repository.Review.ReviewRepository;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {
    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    private final UserEntity recipient = UserEntity.builder().id(1L).build();
    private final UserEntity author = UserEntity.builder().id(2L).build();

    @Test
    void getByIdTest_ok() {
        long id = 1L;
        ReviewEntity reviewEntity = ReviewEntity.builder().id(id).recipient(recipient).author(author).build();
        ReviewDto reviewDto = ReviewConverter.toDto(reviewEntity);

        when(reviewRepository.findById(id)).thenReturn(Optional.of(reviewEntity));

        ReviewDto actual = reviewService.getById(id);

        assertEquals(reviewDto, actual);
        assertEquals(actual.getId(), reviewDto.getId());
        verify(reviewRepository, times(1)).findById(id);
    }

    @Test
    public void getByIdTest_notFound() {
        long id = 1L;

        when(reviewRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> reviewService.getById(id));
        verify(reviewRepository, times(1)).findById(id);
    }

    @Test
    void getAllTest_ok() {
        ReviewEntity review1 = ReviewEntity.builder().id(1L).recipient(recipient).author(author).build();
        ReviewEntity review2 = ReviewEntity.builder().id(2L).recipient(recipient).author(author).build();

        List<ReviewEntity> reviewEntities = List.of(review1, review2);
        List<ReviewDto> expectedDtos = List.of(ReviewConverter.toDto(review1), ReviewConverter.toDto(review2));

        when(reviewRepository.findAll()).thenReturn(reviewEntities);

        List<ReviewDto> actualDtos = reviewService.getAll();

        assertEquals(expectedDtos.size(), actualDtos.size());
        assertEquals(expectedDtos, actualDtos);
        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    void getByRecipientIdTest_ok() {
        long id = 1L;
        long recipientId = recipient.getId();
        ReviewEntity review1 = ReviewEntity.builder().id(id).recipient(recipient).author(author).build();

        List<ReviewEntity> reviewEntities = List.of(review1);
        List<ReviewDto> expectedDtos = List.of(ReviewConverter.toDto(review1));

        when(reviewRepository.getByRecipientId(recipientId)).thenReturn(reviewEntities);

        List<ReviewDto> actual = reviewService.getByRecipientId(recipientId);

        assertEquals(1, actual.size());
        assertEquals(recipientId, actual.get(0).getId());
        assertEquals(expectedDtos.size(), actual.size());
        assertEquals(actual, expectedDtos);
        verify(reviewRepository, times(1)).getByRecipientId(recipientId);
    }

    @Test
    void updateTest_ok() {
        long id = 1L;
        ReviewEntity reviewEntity = ReviewEntity.builder().id(id).recipient(recipient).author(author).build();
        ReviewDto reviewDto = ReviewConverter.toDto(reviewEntity);

        when(reviewRepository.update(any(ReviewEntity.class))).thenReturn(reviewEntity);

        ReviewDto actual = reviewService.update(reviewDto);

        assertEquals(reviewDto, actual);
        verify(reviewRepository, times(1)).update(any(ReviewEntity.class));
    }

    @Test
    void deleteByIdTest_ok() {
        long id = 1L;
        ReviewEntity reviewEntity = ReviewEntity.builder().id(id).recipient(recipient).author(author).build();
        recipient.setReviewsReceived(List.of(reviewEntity));
        when(reviewRepository.findById(id)).thenReturn(Optional.of(reviewEntity));

        doNothing().when(reviewRepository).deleteById(id);

        reviewService.deleteById(id);

        verify(reviewRepository, times(1)).findById(id);
        verify(reviewRepository, times(1)).deleteById(id);
    }
}
