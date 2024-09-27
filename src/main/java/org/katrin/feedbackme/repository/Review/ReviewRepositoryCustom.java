package org.katrin.feedbackme.repository.Review;

import org.katrin.feedbackme.entity.ReviewEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepositoryCustom {
    ReviewEntity update(ReviewEntity reviewEntity);
}
