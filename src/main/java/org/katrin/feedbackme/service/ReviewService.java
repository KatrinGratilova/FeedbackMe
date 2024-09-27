package org.katrin.feedbackme.service;

import lombok.RequiredArgsConstructor;
import org.katrin.feedbackme.converter.ReviewConverter;
import org.katrin.feedbackme.dto.ReviewDto;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.katrin.feedbackme.entity.UserEntity;
import org.katrin.feedbackme.repository.Review.ReviewRepository;
import org.katrin.feedbackme.repository.User.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

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

    public void deleteById(long id) {
        ReviewEntity review = reviewRepository.findById(id).orElseThrow();
        UserEntity recipient = review.getRecipient();
        reviewRepository.deleteById(id);
        recipient.recalculateAvgRating();
        userRepository.save(recipient);
    }
}
