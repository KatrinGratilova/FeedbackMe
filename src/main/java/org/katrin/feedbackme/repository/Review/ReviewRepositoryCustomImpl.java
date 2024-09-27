package org.katrin.feedbackme.repository.Review;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.katrin.feedbackme.entity.ReviewEntity;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class ReviewRepositoryCustomImpl implements ReviewRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public ReviewEntity update(ReviewEntity reviewEntity) {
        ReviewEntity originalReview = entityManager.find(ReviewEntity.class, reviewEntity.getId());
        if (originalReview == null)
            throw new EntityNotFoundException("Review entity with id " + reviewEntity.getId() + " not found");

        originalReview.setRating(reviewEntity.getRating());
        originalReview.setComment(reviewEntity.getComment());

        return entityManager.merge(originalReview);
    }
}
