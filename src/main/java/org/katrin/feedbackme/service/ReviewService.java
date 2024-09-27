package org.katrin.feedbackme.service;

import lombok.RequiredArgsConstructor;
import org.katrin.feedbackme.converter.ReviewConverter;
import org.katrin.feedbackme.dto.ReviewDto;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.repository.Review.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewDto getById(long id) {
        return reviewRepository.findById(id).map(ReviewConverter::toDto).orElseThrow();
    }

    public List<ReviewDto> getAll() {
        return reviewRepository.findAll().stream().map(ReviewConverter::toDto).toList();
    }

    public List<ReviewDto> getByRecipientId(long id) {
        return reviewRepository.getByRecipientId(id).stream().map(ReviewConverter::toDto).toList();
    }

    public List<ReviewDto> getByAuthorId(long id) {
        return reviewRepository.getByAuthorId(id).stream().map(ReviewConverter::toDto).toList();
    }

    public ReviewDto update(ReviewDto reviewDto) {
        ReviewEntity reviewEntity = reviewRepository.update(ReviewConverter.toEntity(reviewDto));
        return ReviewConverter.toDto(reviewEntity);
    }

    public void deleteReview(long id) {
        reviewRepository.deleteById(id);
    }
}
